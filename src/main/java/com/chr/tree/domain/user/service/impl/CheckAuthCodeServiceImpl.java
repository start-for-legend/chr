package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.entity.Authentication;
import com.chr.tree.domain.user.repository.AuthenticationRepository;
import com.chr.tree.domain.user.service.CheckAuthCodeService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class CheckAuthCodeServiceImpl implements CheckAuthCodeService {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public void execute(String email, int code) {
        // TODO : Custom Exception
        Authentication auth = authenticationRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        if (auth.getAuthCode() == code) {
            auth.setChecked();
        } else throw new RuntimeException("Not Invalid Code");
    }
}
