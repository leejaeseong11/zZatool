package com.timekiller.zzatool.result.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResultView is a Querydsl query type for ResultView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResultView extends EntityPathBase<ResultView> {

    private static final long serialVersionUID = 20965018L;

    public static final QResultView resultView = new QResultView("resultView");

    public final NumberPath<Long> resultId = createNumber("resultId", Long.class);

    public final NumberPath<Long> resultViewId = createNumber("resultViewId", Long.class);

    public final NumberPath<Long> viewId = createNumber("viewId", Long.class);

    public QResultView(String variable) {
        super(ResultView.class, forVariable(variable));
    }

    public QResultView(Path<? extends ResultView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResultView(PathMetadata metadata) {
        super(ResultView.class, metadata);
    }

}

