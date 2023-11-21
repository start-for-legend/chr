package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class ExistByEmailException extends ApplicationException {

    public ExistByEmailException() {
        super(ErrorCode.EXIST_BY_EMAIL);
    }
}
