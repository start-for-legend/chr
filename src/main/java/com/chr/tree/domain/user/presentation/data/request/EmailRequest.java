package com.chr.tree.domain.user.presentation.data.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    @Email(message = "Invalid email type")
    private String email;
}
