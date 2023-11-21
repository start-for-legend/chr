package com.chr.tree.domain.comment.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotAllowedTypeException extends ApplicationException {

    public NotAllowedTypeException() {
        super(ErrorCode.NOT_ALLOWED_TYPE);
    }
}
