package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.presentation.data.request.SignupRequest;
import com.chr.tree.domain.user.entity.Authentication;
import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.exception.ExistByEmailException;
import com.chr.tree.domain.user.exception.ExistByNickNameException;
import com.chr.tree.domain.user.exception.NotCertifiedEmailException;
import com.chr.tree.domain.user.repository.AuthenticationRepository;
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
    private final AuthenticationRepository authenticationRepository;

    @Override
    public void execute(SignupRequest signupRequest) {
        Authentication auth = authenticationRepository.findByEmail(signupRequest.getEmail()).orElseThrow(RuntimeException::new);
        if (!auth.isChecked()) {
            throw new NotCertifiedEmailException();
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ExistByEmailException();
        }

        if (userRepository.existsByNickName(signupRequest.getNickName())) {
            throw new ExistByNickNameException();
        }

        User user = User.builder()
                .email(signupRequest.getEmail())
                .nickName(signupRequest.getNickName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        userRepository.save(user);
    }
}
