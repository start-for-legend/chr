package com.chr.tree.global.error.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends ApplicationException {

    public TokenExpiredException() {
        super(ErrorCode.TOKEN_IS_EXPIRED);
    }
}
