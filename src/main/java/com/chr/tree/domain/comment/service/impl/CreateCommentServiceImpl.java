package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.enums.CommentType;
import com.chr.tree.domain.comment.exception.NotAllowedCommentMyselfException;
import com.chr.tree.domain.comment.exception.NotAllowedTypeException;
import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;
import com.chr.tree.domain.comment.repository.CommentRepository;
import com.chr.tree.domain.comment.service.CreateCommentService;
import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.exception.UserNotFoundException;
import com.chr.tree.domain.user.repository.UserRepository;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@ServiceWithTransactional
public class CreateCommentServiceImpl implements CreateCommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    private static final List<String> ALLOWED_TYPE = List.of("CANDY", "BELL", "RING");

    @Override
    public void execute(Long userId, CommentRequest request) {
        if (Objects.equals(userId, userUtil.currentUser().getUserId())) {
            throw new NotAllowedCommentMyselfException();
        }

        if (!ALLOWED_TYPE.contains(request.getCommentType())) {
            throw new NotAllowedTypeException();
        }

        Comment comment = Comment.builder()
                .comment(request.getComment())
                .commentType(CommentType.from(request.getCommentType()))
                .name(request.getName())
                .user(check(userId))
                .build();

        commentRepository.save(comment);
    }

    private User check(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
