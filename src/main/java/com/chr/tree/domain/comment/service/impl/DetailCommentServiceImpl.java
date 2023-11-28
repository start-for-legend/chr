package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.exception.NotFoundCommentException;
import com.chr.tree.domain.comment.exception.PermissionDeniedReadException;
import com.chr.tree.domain.comment.presentation.data.response.DetailCommentResponse;
import com.chr.tree.domain.comment.repository.CommentRepository;
import com.chr.tree.domain.comment.service.DetailCommentService;
import com.chr.tree.global.annotation.ReadOnlyService;
import com.chr.tree.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailCommentServiceImpl implements DetailCommentService {

    private final CommentRepository commentRepository;
    private final UserUtil userUtil;

    @Override
    public DetailCommentResponse execute(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);

        if (!Objects.equals(comment.getUser().getUserId(), userUtil.currentUser().getUserId())) {
            throw new PermissionDeniedReadException();
        }

        return DetailCommentResponse.builder()
                .commentId(commentId)
                .comment(comment.getComment())
                .name(comment.getName())
                .build();
    }
}
