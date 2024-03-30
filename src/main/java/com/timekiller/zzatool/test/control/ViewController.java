package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.exception.AddException;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ViewService viewService;

    @PostMapping("/view")
    public ResponseEntity<?> createView(@RequestBody ViewDTO viewDTO) {
        try {
            viewService.createView(viewDTO);
            return ResponseEntity.ok().body("보기 생성이 완료되었습니다.");
        } catch (AddException e) {
            return ResponseEntity.badRequest().body("보기 생성에 실패했습니다.");
        }
    }
}
