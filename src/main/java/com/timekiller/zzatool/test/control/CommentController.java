package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.CommentDTO;
import com.timekiller.zzatool.test.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(
            HttpServletRequest request,
            @RequestParam("value") String value,
            @RequestParam("testId") Long testId) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String id = (String) session.getAttribute("id"); // 세션에서 ID 추출
            if (id == null) id = "익명";

            Date currentDate =
                    Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

            CommentDTO commentDTO =
                    CommentDTO.builder()
                            .testId(testId)
                            .commentDate(currentDate)
                            .commentWriter(id)
                            .commentContent(value)
                            .build();

            commentService.addComment(commentDTO);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
