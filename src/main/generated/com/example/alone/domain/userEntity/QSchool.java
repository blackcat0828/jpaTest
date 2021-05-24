package com.example.alone.domain.userEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchool is a Querydsl query type for School
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchool extends EntityPathBase<School> {

    private static final long serialVersionUID = 983016846L;

    public static final QSchool school = new QSchool("school");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<Student, QStudent> students = this.<Student, QStudent>createSet("students", Student.class, QStudent.class, PathInits.DIRECT2);

    public final StringPath telnumber = createString("telnumber");

    public QSchool(String variable) {
        super(School.class, forVariable(variable));
    }

    public QSchool(Path<? extends School> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchool(PathMetadata metadata) {
        super(School.class, metadata);
    }

}

