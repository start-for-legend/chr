package com.chr.tree.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@AllArgsConstructor
@RedisHash("refreshToken")
public class RefreshToken {

    @Id
    private Long userId;

    @Indexed
    private String accessToken;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long expiresIn;

    public void setToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
