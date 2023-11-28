package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.response.LinkResponse;
import com.chr.tree.domain.comment.service.CreateLinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "LINK API", description = "link 관련 API")
@RequestMapping("/v1/link")
@RestController
@RequiredArgsConstructor
public class LinkController {

    private final CreateLinkService createLinkService;

    @Operation(summary = "make link", description = "Link 생성")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Link 생성",
                    content = @Content(schema = @Schema(implementation = LinkResponse.class))
            ),
    })
    @GetMapping
    public ResponseEntity<LinkResponse> getLink() {
        var response = createLinkService.execute();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
