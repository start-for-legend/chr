package com.chr.tree.domain.user.service;

public interface CheckAuthCodeService {

    void execute(String email, int code);
}
