package com.chr.tree.domain.user.service;

import com.chr.tree.domain.user.presentation.data.request.SignupRequest;

public interface SignupService {

    void execute(SignupRequest signupRequest);
}
