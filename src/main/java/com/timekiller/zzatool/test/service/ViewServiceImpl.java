package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.ViewRepository;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.entity.View;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;

    @Override
    public void createView(ViewDTO viewDTO) {
        viewRepository.save(
                View.builder()
                        .viewId(viewDTO.getViewId())
                        .viewNumber(viewDTO.getViewNumber())
                        .viewContent(viewDTO.getViewContent())
                        .quizId(viewDTO.getQuizId())
                        .isCorrect(viewDTO.getIsCorrect())
                        .build());
    }
}
