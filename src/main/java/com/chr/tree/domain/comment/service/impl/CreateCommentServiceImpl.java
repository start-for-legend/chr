package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.enums.CommentType;
import com.chr.tree.domain.comment.exception.NotAllowedTypeException;
import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;
import com.chr.tree.domain.comment.repository.CommentRepository;
import com.chr.tree.domain.comment.service.CreateCommentService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactional
public class CreateCommentServiceImpl implements CreateCommentService {

    private final CommentRepository commentRepository;

    private static final List<String> ALLOWED_TYPE = List.of("CANDY", "BELL", "RING");

    @Override
    public void execute(Long userId, CommentRequest request) {
        if (!ALLOWED_TYPE.contains(request.getCommentType())) {
            throw new NotAllowedTypeException();
        }

        Comment comment = Comment.builder()
                .comment(request.getComment())
                .commentType(CommentType.from(request.getCommentType()))
                .name(request.getName())
                .build();

        commentRepository.save(comment);
    }
}
