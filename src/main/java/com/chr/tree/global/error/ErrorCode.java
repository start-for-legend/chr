package com.chr.tree.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //SERVER ERROR
    UNKNOWN_ERROR("server error", 500),
    NOT_SEND_EMAIL("email is not sent", 500),

    //EMAIL AUTHENTICATION
    AUTH_NOT_FOUND("not exists auth", 404),
    NOT_CHECKED_EMAIL("not certified email", 403),
    INVALID_AUTH_CODE("not invalid code", 403),

    //USER AUTH
    USER_NOT_FOUND("user not found", 404),
    INVALID_PASSWORD("invalid password", 403),
    EXIST_BY_EMAIL("exists user by request email", 409),
    EXIST_BY_NICKNAME("exists user by request nick name", 409),

    //TOKEN
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    //COMMENT
    NOT_ALLOWED_TYPE("not allowed comment type", 400),
    NOT_FOUND_COMMENT("not found comment", 404),
    NOT_COMMENT_MYSELF("not comment myself", 403),
    PERMISSION_DENIED_READ("not allowed other comment", 403),
    ;

    private final String message;
    private final int code;
}
