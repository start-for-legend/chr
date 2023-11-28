package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class ExistByNickNameException extends ApplicationException {

    public ExistByNickNameException() {
        super(ErrorCode.EXIST_BY_NICKNAME);
    }
}
