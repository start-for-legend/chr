package com.chr.tree.domain.comment.service;

import com.chr.tree.domain.comment.presentation.data.response.DetailCommentResponse;

public interface DetailCommentService {

    DetailCommentResponse execute(Long commentId);
}
