package com.chr.tree.domain.auth.repository;

import com.chr.tree.domain.auth.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, Long> {

    boolean existsByRefreshToken(String refreshToken);

    boolean existsByAccessToken(String accessToken);
}
