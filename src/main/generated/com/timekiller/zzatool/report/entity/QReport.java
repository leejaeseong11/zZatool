package com.timekiller.zzatool.report.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = -983084989L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final com.timekiller.zzatool.test.entity.QQuiz quiz;

    public final DateTimePath<java.sql.Timestamp> reportDate = createDateTime("reportDate", java.sql.Timestamp.class);

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    public final StringPath reportReason = createString("reportReason");

    public final com.timekiller.zzatool.test.entity.QTest test;

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quiz = inits.isInitialized("quiz") ? new com.timekiller.zzatool.test.entity.QQuiz(forProperty("quiz")) : null;
        this.test = inits.isInitialized("test") ? new com.timekiller.zzatool.test.entity.QTest(forProperty("test")) : null;
    }

}

