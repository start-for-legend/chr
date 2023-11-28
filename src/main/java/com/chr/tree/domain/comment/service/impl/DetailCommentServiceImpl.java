package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.exception.NotFoundCommentException;
import com.chr.tree.domain.comment.presentation.data.response.DetailCommentResponse;
import com.chr.tree.domain.comment.repository.CommentRepository;
import com.chr.tree.domain.comment.service.DetailCommentService;
import com.chr.tree.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailCommentServiceImpl implements DetailCommentService {

    private final CommentRepository commentRepository;

    @Override
    public DetailCommentResponse execute(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);

        return DetailCommentResponse.builder()
                .commentId(commentId)
                .comment(comment.getComment())
                .name(comment.getName())
                .build();
    }
}
