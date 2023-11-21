package com.chr.tree.domain.user.presentation;

import com.chr.tree.domain.user.presentation.data.request.CheckRequest;
import com.chr.tree.domain.user.presentation.data.request.EmailRequest;
import com.chr.tree.domain.user.service.CheckAuthCodeService;
import com.chr.tree.domain.user.service.SendAuthenticationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailController {

    private final SendAuthenticationCodeService sendAuthenticationCodeService;
    private final CheckAuthCodeService checkAuthCodeService;

    @PostMapping
    public ResponseEntity<Void> authCodeSend(@RequestBody EmailRequest emailRequest) {
        sendAuthenticationCodeService.execute(emailRequest.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Void> checkCode(@RequestBody CheckRequest checkRequest) {
        checkAuthCodeService.execute(checkRequest.getEmail(), checkRequest.getCode());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
