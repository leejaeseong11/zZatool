package com.timekiller.zzatool.test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestHashtag is a Querydsl query type for TestHashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTestHashtag extends EntityPathBase<TestHashtag> {

    private static final long serialVersionUID = 1753251213L;

    public static final QTestHashtag testHashtag = new QTestHashtag("testHashtag");

    public final StringPath tagContent = createString("tagContent");

    public final NumberPath<Long> testHashtagId = createNumber("testHashtagId", Long.class);

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public QTestHashtag(String variable) {
        super(TestHashtag.class, forVariable(variable));
    }

    public QTestHashtag(Path<? extends TestHashtag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestHashtag(PathMetadata metadata) {
        super(TestHashtag.class, metadata);
    }

}

