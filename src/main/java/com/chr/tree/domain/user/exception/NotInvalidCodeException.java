package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotInvalidCodeException extends ApplicationException {

    public NotInvalidCodeException() {
        super(ErrorCode.INVALID_AUTH_CODE);
    }
}
