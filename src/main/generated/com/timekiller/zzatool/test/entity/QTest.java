package com.timekiller.zzatool.test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 1883947327L;

    public static final QTest test = new QTest("test");

    public final ListPath<Comment, QComment> commentList = this.<Comment, QComment>createList("commentList", Comment.class, QComment.class, PathInits.DIRECT2);

    public final ListPath<TestHashtag, QTestHashtag> hashtagList = this.<TestHashtag, QTestHashtag>createList("hashtagList", TestHashtag.class, QTestHashtag.class, PathInits.DIRECT2);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final ListPath<Quiz, QQuiz> quizList = this.<Quiz, QQuiz>createList("quizList", Quiz.class, QQuiz.class, PathInits.DIRECT2);

    public final NumberPath<Long> testCount = createNumber("testCount", Long.class);

    public final DateTimePath<java.util.Date> testDate = createDateTime("testDate", java.util.Date.class);

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public final StringPath testImage = createString("testImage");

    public final NumberPath<Integer> testStatus = createNumber("testStatus", Integer.class);

    public final StringPath testTitle = createString("testTitle");

    public QTest(String variable) {
        super(Test.class, forVariable(variable));
    }

    public QTest(Path<? extends Test> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTest(PathMetadata metadata) {
        super(Test.class, metadata);
    }

}

