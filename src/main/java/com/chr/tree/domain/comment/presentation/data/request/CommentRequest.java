package com.chr.tree.domain.comment.presentation.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "name is necessary")
    private String name;

    @NotBlank(message = "comment is necessary")
    private String comment;

    private String commentType;
}
