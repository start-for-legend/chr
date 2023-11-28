package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotCertifiedEmailException extends ApplicationException {

    public NotCertifiedEmailException() {
        super(ErrorCode.NOT_CHECKED_EMAIL);
    }
}
