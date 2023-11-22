package com.chr.tree.domain.comment.service;

import com.chr.tree.domain.comment.presentation.data.response.CommentListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetCommentsService {

    Page<CommentListResponse> execute(Pageable pageable, Long userId);
}
