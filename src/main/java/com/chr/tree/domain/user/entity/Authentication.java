package com.chr.tree.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Email
    @Column(nullable = false)
    private String email;

    private boolean checked;

    @Column(nullable = false, name = "auth_code")
    private int authCode;

    public void setAuthCode(int code) {
        this.authCode = code;
    }

    public void setChecked() {
        this.checked = true;
    }
}
