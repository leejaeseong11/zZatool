package com.timekiller.zzatool.test.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyTestDTO {
    private Long testId; // 테스트 아이디
    private String testTitle; // 테스트 제목
    private Date testDate; // 테스트 생성일
    private Long testCount; // 테스트 조회 수
}
