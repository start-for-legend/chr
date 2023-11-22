package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.comment.exception.NotAllowedTypeException;
import com.chr.tree.domain.comment.presentation.data.response.CommentListResponse;
import com.chr.tree.domain.comment.presentation.data.response.CommentResponse;
import com.chr.tree.domain.comment.service.GetCommentsService;
import com.chr.tree.global.annotation.ReadOnlyService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.chr.tree.domain.comment.entity.QComment.comment1;

@ReadOnlyService
@RequiredArgsConstructor
public class GetCommentsServiceImpl implements GetCommentsService {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CommentListResponse> execute(Pageable pageable, Long userId) {
        List<Comment> commentList = queryFactory
                .selectFrom(comment1)
                .where(lt2(userId))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        List<CommentResponse> commentResponseList = from(commentList);

        return new PageImpl<>(Collections.singletonList(new CommentListResponse(commentResponseList)), pageable, countComments(userId));
    }

    private Long countComments(Long userId) {
        return queryFactory
                .selectFrom(comment1)
                .where(lt2(userId))
                .fetchCount();
    }


    private BooleanExpression lt2(Long userId) {
        if (userId == null) {
            throw new NotAllowedTypeException();
        }
        return comment1.user.userId.eq(userId);
    }

    public static List<CommentResponse> from(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentResponse::commentResponse) // Assuming you have a from method in CommentResponse
                .collect(Collectors.toList());
    }
}
