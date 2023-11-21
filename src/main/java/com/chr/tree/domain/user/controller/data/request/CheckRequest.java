package com.chr.tree.domain.user.controller.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckRequest {

    @Email(message = "Not Invalid email type")
    private String email;

    @NotNull(message = "Not Blank")
    private int code;
}
