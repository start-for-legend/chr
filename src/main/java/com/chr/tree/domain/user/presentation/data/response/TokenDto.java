package com.chr.tree.domain.user.presentation.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private String refreshToken;
}
