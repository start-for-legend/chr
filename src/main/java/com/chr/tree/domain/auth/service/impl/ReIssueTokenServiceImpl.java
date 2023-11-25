package com.chr.tree.domain.auth.service.impl;

import com.chr.tree.domain.auth.entity.RefreshToken;
import com.chr.tree.domain.auth.repository.RefreshTokenRepository;
import com.chr.tree.domain.auth.service.ReIssueTokenService;
import com.chr.tree.domain.user.presentation.data.response.TokenDto;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.error.exception.TokenExpiredException;
import com.chr.tree.global.error.exception.TokenNotValidException;
import com.chr.tree.global.security.jwt.TokenIssuer;
import com.chr.tree.global.security.jwt.TokenParser;
import com.chr.tree.global.util.UserUtil;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class ReIssueTokenServiceImpl implements ReIssueTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenIssuer tokenIssuer;
    private final TokenParser tokenParser;
    private final UserUtil userUtil;

    @Override
    public TokenDto execute(Cookie[] cookies) {
        String token = null;
        for (Cookie cookie : cookies) {
            if ("refreshToken".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            throw new TokenNotValidException();
        }

        System.out.println("token = " + token);

        RefreshToken res = refreshTokenRepository.findById(userUtil.currentUser().getUserId()).orElseThrow(TokenExpiredException::new);

        String email = tokenParser.extractEmailWithClaim(res.getRefreshToken());
        String accessToken = tokenIssuer.generateAccessToken(email);
        String refreshToken = tokenIssuer.generateRefreshToken(email);

        setNewRefresh(userUtil.currentUser().getUserId(), accessToken, refreshToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void setNewRefresh(Long userId, String accessToken, String refreshToken) {
        RefreshToken redis = refreshTokenRepository.findById(userId).orElseThrow(TokenExpiredException::new);

        redis.setToken(accessToken, refreshToken);
        refreshTokenRepository.save(redis);
    }
}
