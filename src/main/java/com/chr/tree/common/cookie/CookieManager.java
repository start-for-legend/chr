package com.chr.tree.common.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieManager {

    private Cookie setCookie(String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);

        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1209600);

        return cookie;
    }

    public void addCookie(String refreshToken, HttpServletResponse response) {
        response.addCookie(setCookie(refreshToken));
    }
}
