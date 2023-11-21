package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.entity.Authentication;
import com.chr.tree.domain.user.exception.AuthNotFoundException;
import com.chr.tree.domain.user.repository.AuthenticationRepository;
import com.chr.tree.domain.user.service.SendAuthenticationCodeService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.util.MailUtil;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;

@ServiceWithTransactional
@RequiredArgsConstructor
public class SendAuthenticationServiceImpl implements SendAuthenticationCodeService {

    private final AuthenticationRepository authenticationRepository;
    private final MailUtil mailUtil;

    @Override
    public void execute(String email) {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(8888) + 1111;

        saveAuth(email, code);
        mailUtil.sendAuthenticationMail(email, code);
    }

    private void saveAuth(String email, int code) {
        if (authenticationRepository.existsByEmail(email)) {
            Authentication auth = authenticationRepository.findByEmail(email).orElseThrow(AuthNotFoundException::new);

            auth.setAuthCode(code);
            authenticationRepository.save(auth);
            return;
        }

        Authentication auth = Authentication.builder()
                .email(email)
                .authCode(code)
                .checked(false)
                .build();

        authenticationRepository.save(auth);
    }
}
