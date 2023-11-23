package com.chr.tree.domain.comment.presentation.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DetailCommentResponse {

    private Long commentId;

    private String name;

    private String comment;
}
