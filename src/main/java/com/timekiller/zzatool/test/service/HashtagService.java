package com.timekiller.zzatool.test.service;

public interface HashtagService {
    /* 해시태그 추가 */
    void addHashtag(String tagContent);

    /* 해시태그 삭제 */
    void removeHashtag(String tagContent);
}
