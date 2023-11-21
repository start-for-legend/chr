package com.chr.tree.domain.user.exception;

import com.chr.tree.global.error.ApplicationException;
import com.chr.tree.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
