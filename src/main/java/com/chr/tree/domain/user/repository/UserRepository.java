package com.chr.tree.domain.user.repository;

import com.chr.tree.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByNickName(String nickName);

    Optional<User> findByEmail(String email);
}
