package com.chr.tree.domain.user.service;

import com.chr.tree.domain.user.controller.data.request.SignupRequest;

public interface SignupService {

    void execute(SignupRequest signupRequest);
}
