package com.chr.tree.domain.user.presentation;

import com.chr.tree.domain.user.presentation.data.request.CheckRequest;
import com.chr.tree.domain.user.presentation.data.request.EmailRequest;
import com.chr.tree.domain.user.service.CheckAuthCodeService;
import com.chr.tree.domain.user.service.SendAuthenticationCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MAIL API", description = "Mail 관련 controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
public class MailController {

    private final SendAuthenticationCodeService sendAuthenticationCodeService;
    private final CheckAuthCodeService checkAuthCodeService;

    @Operation(summary = "send code", description = "이메일 인증 코드 전송")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "EMAIL CODE CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "Authentication not found by exists request email"),
    })
    @PostMapping
    public ResponseEntity<Void> authCodeSend(@RequestBody EmailRequest emailRequest) {
        sendAuthenticationCodeService.execute(emailRequest.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "check code", description = "이메일 인증 코드 확인")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "Authentication not found by exists request email"),
            @ApiResponse(responseCode = "409", description = "INVALID AUTH CODE"),
    })
    @PatchMapping
    public ResponseEntity<Void> checkCode(@RequestBody CheckRequest checkRequest) {
        checkAuthCodeService.execute(checkRequest.getEmail(), checkRequest.getCode());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
