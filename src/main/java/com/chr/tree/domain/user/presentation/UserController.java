package com.chr.tree.domain.user.presentation;

import com.chr.tree.common.cookie.CookieManager;
import com.chr.tree.domain.auth.service.LogoutService;
import com.chr.tree.domain.auth.service.ReIssueTokenService;
import com.chr.tree.domain.user.presentation.data.request.LoginRequest;
import com.chr.tree.domain.user.presentation.data.request.SignupRequest;
import com.chr.tree.domain.user.presentation.data.response.LoginResponse;
import com.chr.tree.domain.user.presentation.data.response.TokenDto;
import com.chr.tree.domain.user.service.LoginService;
import com.chr.tree.domain.user.service.SignupService;
import com.chr.tree.global.security.jwt.TokenIssuer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final TokenIssuer tokenIssuer;
    private final CookieManager cookieManager;
    private final LogoutService logoutService;
    private final ReIssueTokenService reIssueTokenService;

    @PostMapping("/new")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest signupRequest) {
        signupService.execute(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse res) {
        TokenDto tokenDto = loginService.execute(loginRequest);

        cookieManager.addCookie(tokenDto.getRefreshToken(), res);

        return new ResponseEntity<>(
                LoginResponse.builder()
                        .accessToken(tokenDto.getAccessToken())
                        .expiredAt(ZonedDateTime.now().plusSeconds(tokenIssuer.getACCESS_TOKEN_EXPIRE_TIME()))
                        .build(),
                HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<LoginResponse> reIssueToken(HttpServletRequest request, HttpServletResponse res) {
        TokenDto tokenDto = reIssueTokenService.execute(request.getCookies());

        cookieManager.addCookie(tokenDto.getRefreshToken(), res);

        return new ResponseEntity<>(
                LoginResponse.builder()
                        .accessToken(tokenDto.getAccessToken())
                        .expiredAt(ZonedDateTime.now().plusSeconds(tokenIssuer.getACCESS_TOKEN_EXPIRE_TIME()))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        logoutService.execute(request.getCookies());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
