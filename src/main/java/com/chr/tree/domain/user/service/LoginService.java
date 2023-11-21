package com.chr.tree.domain.user.service;

import com.chr.tree.domain.user.presentation.data.request.LoginRequest;
import com.chr.tree.domain.user.presentation.data.response.TokenDto;

public interface LoginService {

    TokenDto execute(LoginRequest loginRequest);
}
