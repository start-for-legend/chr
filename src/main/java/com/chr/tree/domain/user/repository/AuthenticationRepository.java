package com.chr.tree.domain.user.repository;

import com.chr.tree.domain.user.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Optional<Authentication> findByEmail(String email);
}
