package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.presentation.data.request.LoginRequest;
import com.chr.tree.domain.user.presentation.data.response.TokenDto;
import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.exception.InvalidPasswordException;
import com.chr.tree.domain.user.exception.UserNotFoundException;
import com.chr.tree.domain.user.repository.UserRepository;
import com.chr.tree.domain.user.service.LoginService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.security.jwt.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@ServiceWithTransactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenIssuer tokenIssuer;

    @Override
    public TokenDto execute(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = tokenIssuer.generateAccessToken(loginRequest.getEmail());
        String refreshToken = tokenIssuer.generateRefreshToken(loginRequest.getEmail());

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
