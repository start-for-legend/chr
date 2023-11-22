package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;
import com.chr.tree.domain.comment.presentation.data.response.CommentListResponse;
import com.chr.tree.domain.comment.service.CreateCommentService;
import com.chr.tree.domain.comment.service.GetCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CreateCommentService createCommentService;
    private final GetCommentsService getCommentsService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long userId) {
        createCommentService.execute(userId, commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<CommentListResponse>> getCommentList(@PathVariable Long userId, @RequestParam(name = "offset") Long offset) {
        Pageable pageable = PageRequest.of(Math.toIntExact(offset), 8);
        var list = getCommentsService.execute(pageable, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
