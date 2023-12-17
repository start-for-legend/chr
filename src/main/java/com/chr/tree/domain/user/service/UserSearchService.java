package com.chr.tree.domain.user.service;

import com.chr.tree.domain.user.presentation.data.response.UserResponse;

public interface UserSearchService {

    UserResponse execute(Long userId);
}
