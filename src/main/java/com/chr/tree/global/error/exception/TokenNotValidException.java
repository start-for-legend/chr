package com.chr.tree.global.error.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotValidException extends ApplicationException {

    public TokenNotValidException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
