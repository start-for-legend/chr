package com.chr.tree.domain.user.service.impl;

import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.exception.UserNotFoundException;
import com.chr.tree.domain.user.presentation.data.response.UserResponse;
import com.chr.tree.domain.user.repository.UserRepository;
import com.chr.tree.domain.user.service.UserSearchService;
import com.chr.tree.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {

    private final UserRepository userRepository;

    @Override
    public UserResponse execute(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return UserResponse.builder()
                .userName(user.getNickName())
                .build();
    }
}
