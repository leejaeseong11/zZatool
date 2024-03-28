package com.timekiller.zzatool.test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QView is a Querydsl query type for View
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QView extends EntityPathBase<View> {

    private static final long serialVersionUID = 1884010322L;

    public static final QView view = new QView("view");

    public final NumberPath<Integer> isCorrect = createNumber("isCorrect", Integer.class);

    public final NumberPath<Long> quizId = createNumber("quizId", Long.class);

    public final StringPath viewContent = createString("viewContent");

    public final NumberPath<Long> viewId = createNumber("viewId", Long.class);

    public final NumberPath<Integer> viewNumber = createNumber("viewNumber", Integer.class);

    public QView(String variable) {
        super(View.class, forVariable(variable));
    }

    public QView(Path<? extends View> path) {
        super(path.getType(), path.getMetadata());
    }

    public QView(PathMetadata metadata) {
        super(View.class, metadata);
    }

}

