package com.timekiller.zzatool.test.dto;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCreateDTO {
    private String testTitle; // 테스트 제목
    private Long memberId; // [FK] 회원 아이디
    private Date testDate; // 테스트 제작일
    private String testImage; // 테스트 이미지

}
