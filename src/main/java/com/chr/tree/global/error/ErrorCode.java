package com.chr.tree.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //UNKNOWN
    UNKNOWN_ERROR("server error", 500)
    ;

    private final String message;
    private final int code;
}
