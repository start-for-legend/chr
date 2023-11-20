package com.chr.tree.domain.comment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CommentType {
    CANDY, TOY, BELL;

    @JsonCreator
    public static CommentType from(String s) {
        return CommentType.valueOf(s.toUpperCase());
    }
}
