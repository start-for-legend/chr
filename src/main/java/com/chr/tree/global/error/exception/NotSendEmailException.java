package com.chr.tree.global.error.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotSendEmailException extends ApplicationException {

    public NotSendEmailException() {
        super(ErrorCode.NOT_SEND_EMAIL);
    }
}
