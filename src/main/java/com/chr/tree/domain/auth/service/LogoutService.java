package com.chr.tree.domain.auth.service;

import jakarta.servlet.http.Cookie;

public interface LogoutService {

    void execute(Cookie[] cookies);
}
