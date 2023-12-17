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
import com.chr.tree.domain.user.service.UserSearchService;
import com.chr.tree.global.security.jwt.TokenIssuer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@Tag(name = "AUTH API", description = "Auth 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class UserController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final TokenIssuer tokenIssuer;
    private final CookieManager cookieManager;
    private final LogoutService logoutService;
    private final UserSearchService userSearchService;
    private final ReIssueTokenService reIssueTokenService;

    @Operation(summary = "sign up", description = "유저 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "403", description = "NOT CHECKED EMAIL"),
            @ApiResponse(responseCode = "409", description = "DUPLICATED DATA"),
    })
    @PostMapping("/new")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest signupRequest) {
        signupService.execute(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "login", description = "유저 로그인")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "SUCCESS LOGIN",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class)),
                    headers = @Header(name = "refreshToken", description = "refreshToken value", required = true)
            ),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "403", description = "INVALID PASSWORD"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND USER BY EMAIL"),
    })
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

    @Operation(summary = "reissue token", description = "유저 토큰 재발급")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class)),
                    headers = @Header(name = "refreshToken", description = "refreshToken value", required = true)
            ),
            @ApiResponse(responseCode = "401", description = "Token InValid, Token Expired"),
    })
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

    @Operation(summary = "logout", description = "유저 로그아웃")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "CREATED"),
            @ApiResponse(responseCode = "401", description = "Token InValid, Token Expired"),
    })
    @DeleteMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        logoutService.execute(request.getCookies());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "search", description = "유저 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Token InValid, Token Expired"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<?> search(@PathVariable Long userId) {
        var response = userSearchService.execute(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
