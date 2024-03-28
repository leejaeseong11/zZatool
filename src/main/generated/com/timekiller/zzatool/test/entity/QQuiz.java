package com.timekiller.zzatool.test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuiz is a Querydsl query type for Quiz
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuiz extends EntityPathBase<Quiz> {

    private static final long serialVersionUID = 1883873026L;

    public static final QQuiz quiz = new QQuiz("quiz");

    public final NumberPath<Long> correctCount = createNumber("correctCount", Long.class);

    public final NumberPath<Float> correctRate = createNumber("correctRate", Float.class);

    public final StringPath quizContent = createString("quizContent");

    public final NumberPath<Long> quizId = createNumber("quizId", Long.class);

    public final StringPath quizImage = createString("quizImage");

    public final NumberPath<Integer> quizNo = createNumber("quizNo", Integer.class);

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public final ListPath<View, QView> viewList = this.<View, QView>createList("viewList", View.class, QView.class, PathInits.DIRECT2);

    public QQuiz(String variable) {
        super(Quiz.class, forVariable(variable));
    }

    public QQuiz(Path<? extends Quiz> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuiz(PathMetadata metadata) {
        super(Quiz.class, metadata);
    }

}

