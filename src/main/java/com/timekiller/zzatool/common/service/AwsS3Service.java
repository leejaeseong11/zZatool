package com.timekiller.zzatool.common.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AwsS3Service {
    private final AmazonS3 amazonS3;
    private final Logger logger = Logger.getLogger(AwsS3Service.class.getName());

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public AwsS3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadImage(MultipartFile file, String filePath) throws Exception {
        String fileName = createFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            uploadFile(inputStream, objectMetadata, fileName, bucket.concat(filePath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "AWS S3 Image Upload Error: " + e.getMessage());
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
        }
        return getFileUrl(fileName, bucket.concat(filePath));
    }

    private String createFileName(String originalFileName) throws Exception {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) throws Exception {
        String extension;
        try {
            extension = fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Image FileType Error: " + e.getMessage());
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다", fileName));
        }

        if (!extension.equals(".jpg") && !extension.equals(".png")) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다", fileName));
        }
        return extension;
    }

    private void uploadFile(InputStream inputStream,
                            ObjectMetadata objectMetadata,
                            String fileName,
                            String bucketPath) {

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketPath, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(putObjectRequest);
    }

    private String getFileUrl(String fileName, String bucketPath) {
        return amazonS3.getUrl(bucketPath, fileName).toString();
    }

    public void deleteImage(String imageUrl, String filePath) {
        try {
            String fileName = extractFileName(imageUrl); // 이미지 URL에서 파일 이름 추출
            deleteFile(fileName, filePath); // 파일 삭제 메소드 호출
        } catch (Exception e) {
            logger.log(Level.SEVERE, "AWS S3 Image Delete Error: " + e.getMessage());
            throw new IllegalArgumentException("이미지 삭제 중 오류가 발생했습니다.");
        }
    }

    private String extractFileName(String imageUrl) {
        String[] parts = imageUrl.split("/");
        return parts[parts.length - 1];
    }


    private void deleteFile(String fileName, String filePath) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket.concat(filePath), fileName));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "AWS S3 File Delete Error: " + e.getMessage());
            throw new IllegalArgumentException("파일 삭제 중 오류가 발생했습니다.");
        }
    }
}