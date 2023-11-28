package com.chr.tree.domain.comment.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundCommentException extends ApplicationException {

    public NotFoundCommentException() {
        super(ErrorCode.NOT_FOUND_COMMENT);
    }
}
