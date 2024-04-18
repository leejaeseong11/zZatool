package com.timekiller.zzatool.result.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Long resultId; // 결과 아이디
    private Long testId; // 테스트 아이디
    private String testTitle; // 테스트 제목
    private Long memberId; // 회원 아이디
    private Integer resultScore; // 결과 점수
    private Date resultDate; // 제출일
    private List<ResultViewDTO> resultViewList; // 결과 보기 목록
}
