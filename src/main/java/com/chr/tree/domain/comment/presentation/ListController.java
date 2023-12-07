package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.response.CommentListResponse;
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
@RequestMapping("/v1")
public class ListController {

    private final GetCommentsService getCommentsService;

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
    @GetMapping("/commentList/{userId}")
    public ResponseEntity<Page<CommentListResponse>> getCommentList(
            @Parameter(description = "User ID") @PathVariable Long userId,
            @Parameter(description = "Offset value") @RequestParam(name = "offset") Long offset)
    {
        Pageable pageable = PageRequest.of(Math.toIntExact(offset), 8);
        var list = getCommentsService.execute(pageable, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
