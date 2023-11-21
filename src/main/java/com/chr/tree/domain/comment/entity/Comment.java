package com.chr.tree.domain.comment.entity;

import com.chr.tree.domain.comment.enums.CommentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 10, name = "comment_name")
    private String name;

    @Column(nullable = false, length = 200)
    private String comment;

    @Enumerated(EnumType.STRING)
    private CommentType commentType;
}
