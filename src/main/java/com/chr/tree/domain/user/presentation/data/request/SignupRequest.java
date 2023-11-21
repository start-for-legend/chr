package com.chr.tree.domain.user.presentation.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String email;

    private String nickName;

    private String password;
}
