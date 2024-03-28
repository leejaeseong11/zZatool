package com.timekiller.zzatool.result.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResult is a Querydsl query type for Result
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResult extends EntityPathBase<Result> {

    private static final long serialVersionUID = -1381078443L;

    public static final QResult result = new QResult("result");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final DateTimePath<java.util.Date> resultDate = createDateTime("resultDate", java.util.Date.class);

    public final NumberPath<Long> resultId = createNumber("resultId", Long.class);

    public final NumberPath<Integer> resultScore = createNumber("resultScore", Integer.class);

    public final ListPath<ResultView, QResultView> resultViewList = this.<ResultView, QResultView>createList("resultViewList", ResultView.class, QResultView.class, PathInits.DIRECT2);

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public QResult(String variable) {
        super(Result.class, forVariable(variable));
    }

    public QResult(Path<? extends Result> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResult(PathMetadata metadata) {
        super(Result.class, metadata);
    }

}

