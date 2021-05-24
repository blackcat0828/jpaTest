package com.example.alone.domain.userEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntityMain is a Querydsl query type for UserEntityMain
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserEntityMain extends EntityPathBase<UserEntityMain> {

    private static final long serialVersionUID = 105908865L;

    public static final QUserEntityMain userEntityMain = new QUserEntityMain("userEntityMain");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public final StringPath userName = createString("userName");

    public QUserEntityMain(String variable) {
        super(UserEntityMain.class, forVariable(variable));
    }

    public QUserEntityMain(Path<? extends UserEntityMain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntityMain(PathMetadata metadata) {
        super(UserEntityMain.class, metadata);
    }

}

