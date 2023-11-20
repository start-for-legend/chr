package com.chr.tree.domain.user.service;

import com.chr.tree.domain.user.controller.data.request.LoginRequest;
import com.chr.tree.domain.user.controller.data.response.TokenDto;

public interface LoginService {

    TokenDto execute(LoginRequest loginRequest);
}
