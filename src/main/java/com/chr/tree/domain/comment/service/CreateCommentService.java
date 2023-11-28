package com.chr.tree.domain.comment.service;

import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;

public interface CreateCommentService {

    void execute(Long userId, CommentRequest request);
}
