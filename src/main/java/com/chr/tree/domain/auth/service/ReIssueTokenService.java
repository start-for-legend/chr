package com.chr.tree.domain.auth.service;

import com.chr.tree.domain.user.presentation.data.response.TokenDto;
import jakarta.servlet.http.Cookie;

public interface ReIssueTokenService {

    TokenDto execute(Cookie[] cookies);
}
