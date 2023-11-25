package com.chr.tree.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("BlackList")
public class BlackList {

    @Id
    private Long userId;

    @Indexed
    private String accessToken;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private long expiredIn;
}
