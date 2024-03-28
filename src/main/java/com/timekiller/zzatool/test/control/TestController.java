package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.TestCreateDTO;
import com.timekiller.zzatool.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    public String main() {
        return "main";
    }

    @PostMapping("/test")
    public ResponseEntity<?> createTest(@RequestPart(name = "testCreateDTO") TestCreateDTO testCreateDTO,
                                        @RequestPart(name = "testImage", required = false) MultipartFile testImage) {
        try {
            testService.createTest(testCreateDTO, testImage);
            return ResponseEntity.ok().body("테스트 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("테스트 생성에 실패했습니다.");
        }
    }
}
