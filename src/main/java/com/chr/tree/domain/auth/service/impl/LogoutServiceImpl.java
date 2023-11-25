package com.chr.tree.domain.auth.service.impl;

import com.chr.tree.domain.auth.entity.BlackList;
import com.chr.tree.domain.auth.entity.RefreshToken;
import com.chr.tree.domain.auth.repository.BlackListRepository;
import com.chr.tree.domain.auth.repository.RefreshTokenRepository;
import com.chr.tree.domain.auth.service.LogoutService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.error.exception.TokenExpiredException;
import com.chr.tree.global.error.exception.TokenNotValidException;
import com.chr.tree.global.security.jwt.TokenIssuer;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final BlackListRepository blackListRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenIssuer tokenIssuer;

    @Override
    public void execute(Cookie[] cookies) {
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

        if (blackListRepository.existsByRefreshToken(token)) {
            throw new TokenExpiredException();
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token).orElseThrow(TokenExpiredException::new);

        BlackList blackList = BlackList
                .builder()
                .accessToken(refreshToken.getAccessToken())
                .refreshToken(refreshToken.getRefreshToken())
                .expiredIn(tokenIssuer.getTokenTimeProperties().getRefreshTime())
                .build();

        refreshTokenRepository.delete(refreshToken);
        blackListRepository.save(blackList);
    }
}
