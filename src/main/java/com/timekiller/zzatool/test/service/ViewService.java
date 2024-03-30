package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.exception.AddException;
import com.timekiller.zzatool.test.dto.ViewDTO;

public interface ViewService {

    /**
     * 사용자가 보기를 생성한다.
     *
     * @param viewDTO 보기
     * @throws AddException
     */
    void createView(ViewDTO viewDTO) throws AddException;
}
