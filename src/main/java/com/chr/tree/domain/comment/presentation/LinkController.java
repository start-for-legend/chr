package com.chr.tree.domain.comment.presentation;

import com.chr.tree.domain.comment.presentation.data.response.LinkResponse;
import com.chr.tree.domain.comment.service.CreateLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/link")
@RestController
@RequiredArgsConstructor
public class LinkController {

    private final CreateLinkService createLinkService;

    @GetMapping
    public ResponseEntity<LinkResponse> getLink() {
        var response = createLinkService.execute();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
