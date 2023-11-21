package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class AuthNotFoundException extends ApplicationException {

    public AuthNotFoundException() {
        super(ErrorCode.AUTH_NOT_FOUND);
    }
}
