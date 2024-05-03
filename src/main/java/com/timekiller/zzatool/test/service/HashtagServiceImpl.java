package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.hashtag.dao.TagRepository;
import com.timekiller.zzatool.hashtag.entity.Hashtag;
import com.timekiller.zzatool.test.dao.HashtagRepository;
import com.timekiller.zzatool.test.entity.TestHashtag;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;
    private final TagRepository tagRepository;

    @Override
    public void addHashtag(Long testId, String hashtag) {
        tagRepository.save(Hashtag.builder().tagContent(hashtag).build());
        hashtagRepository.save(
                TestHashtag.builder()
                        .tagContent(Hashtag.builder().tagContent(hashtag).build())
                        .testId(testId)
                        .build());
    }

    @Override
    public void removeHashtag(Long testId) {
        hashtagRepository.deleteByTestId(testId);
    }
}
