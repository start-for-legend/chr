package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.controller.data.request.SignupRequest;
import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.repository.UserRepository;
import com.chr.tree.domain.user.service.SignupService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@ServiceWithTransactional
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Exists Account with your request email");
        }

        if (userRepository.existsByNickName(signupRequest.getNickName())) {
            throw new RuntimeException("Exists Account with your request nickName");
        }

        User user = User.builder()
                .email(signupRequest.getEmail())
                .nickName(signupRequest.getNickName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        userRepository.save(user);
    }
}
