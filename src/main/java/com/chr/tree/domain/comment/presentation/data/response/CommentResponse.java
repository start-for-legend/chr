package com.chr.tree.domain.comment.presentation.data.response;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.enums.CommentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponse {

    private Long commentId;

    private String name;

    private CommentType commentType;

    public static CommentResponse commentResponse(Comment comment) {

        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .name(comment.getName())
                .commentType(comment.getCommentType())
                .build();
    }
}
