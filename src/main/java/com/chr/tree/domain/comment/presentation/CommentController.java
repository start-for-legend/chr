package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;
import com.chr.tree.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CreateCommentService createCommentService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long userId) {
        createCommentService.execute(userId, commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
