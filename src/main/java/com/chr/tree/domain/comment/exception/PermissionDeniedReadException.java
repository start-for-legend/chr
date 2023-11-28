package com.chr.tree.domain.comment.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class PermissionDeniedReadException extends ApplicationException {

    public PermissionDeniedReadException() {
        super(ErrorCode.PERMISSION_DENIED_READ);
    }
}
