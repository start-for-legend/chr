package com.chr.tree.domain.user.service;

public interface SendAuthenticationCodeService {

    void execute(String email);
}
