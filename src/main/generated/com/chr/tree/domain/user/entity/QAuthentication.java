package com.chr.tree.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthentication is a Querydsl query type for Authentication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthentication extends EntityPathBase<Authentication> {

    private static final long serialVersionUID = -1807930840L;

    public static final QAuthentication authentication = new QAuthentication("authentication");

    public final NumberPath<Integer> authCode = createNumber("authCode", Integer.class);

    public final NumberPath<Long> authId = createNumber("authId", Long.class);

    public final BooleanPath checked = createBoolean("checked");

    public final StringPath email = createString("email");

    public QAuthentication(String variable) {
        super(Authentication.class, forVariable(variable));
    }

    public QAuthentication(Path<? extends Authentication> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthentication(PathMetadata metadata) {
        super(Authentication.class, metadata);
    }

}

