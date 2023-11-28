package com.chr.tree.domain.comment.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotAllowedCommentMyselfException extends ApplicationException {

    public NotAllowedCommentMyselfException() {
        super(ErrorCode.NOT_COMMENT_MYSELF);
    }
}
