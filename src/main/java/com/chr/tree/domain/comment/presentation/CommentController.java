package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.request.CommentRequest;
import com.chr.tree.domain.comment.presentation.data.response.CommentListResponse;
import com.chr.tree.domain.comment.presentation.data.response.DetailCommentResponse;
import com.chr.tree.domain.comment.service.CreateCommentService;
import com.chr.tree.domain.comment.service.DetailCommentService;
import com.chr.tree.domain.comment.service.GetCommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "COMMENT API", description = "Comment 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentController {

    private final CreateCommentService createCommentService;
    private final GetCommentsService getCommentsService;
    private final DetailCommentService detailCommentService;

    @Operation(summary = "create comment", description = "comment 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED" ),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, NOT ALLOWED TYPE"),
            @ApiResponse(responseCode = "403", description = "NOT ALLOWED COMMENT MYSELF"),
            @ApiResponse(responseCode = "404", description = "RECEIVED USER NOT FOUND"),
    })
    @PostMapping("/{userId}")
    public ResponseEntity<Void> createComment(
            @RequestBody CommentRequest commentRequest,
            @Parameter(description = "user id", required = true) @PathVariable Long userId) {
        createCommentService.execute(userId, commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "get comment list", description = "get comment list")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "GET COMMENT LIST",
                    content = @Content(schema = @Schema(implementation = CommentListResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "403", description = "NOT ALLOWED USERID"),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<Page<CommentListResponse>> getCommentList(
            @Parameter(description = "User ID") @PathVariable Long userId,
            @Parameter(description = "Offset value") @RequestParam(name = "offset") Long offset)
    {
        Pageable pageable = PageRequest.of(Math.toIntExact(offset), 8);
        var list = getCommentsService.execute(pageable, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "comment detail", description = "comment detail")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "GET COMMENT DETAIL",
                    content = @Content(schema = @Schema(implementation = DetailCommentResponse.class))
            ),
            @ApiResponse(responseCode = "403", description = "PERMISSION DENIED READ"),
            @ApiResponse(responseCode = "404", description = "USER NOT FOUND"),
    })
    @GetMapping("/{userId}/{commentId}")
    public ResponseEntity<DetailCommentResponse> getCommentDetail
            (@Parameter(description = "comment id", required = true) @PathVariable Long commentId)
    {
        var response = detailCommentService.execute(commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}