package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> createTest(@RequestPart(name = "testDTO") TestDTO testDTO,
                                        @RequestPart(name = "testImage", required = false) MultipartFile testImage) {
        try {
            testService.createTest(testDTO, testImage);
            return ResponseEntity.ok().body("테스트 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("테스트 생성에 실패했습니다.");
        }
    }

    @DeleteMapping("/test/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId, @RequestParam Long memberId) {
        try {
            testService.deleteTest(testId, memberId);
            return ResponseEntity.ok().body("테스트 삭제가 완료되었습니다.");
        } catch (RemoveException e) {
            return ResponseEntity.badRequest().body("테스트를 삭제할 수 없습니다.");
        }
    }
}
