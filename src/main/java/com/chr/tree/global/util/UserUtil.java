package com.chr.tree.global.util;

import com.chr.tree.domain.user.entity.User;
import com.chr.tree.domain.user.exception.UserNotFoundException;
import com.chr.tree.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByNickName(email).orElseThrow(UserNotFoundException::new);
    }
}
