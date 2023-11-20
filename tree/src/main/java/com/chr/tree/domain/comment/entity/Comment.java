package com.chr.tree.domain.comment.entity;

import com.chr.tree.domain.comment.enums.CommentType;
import com.chr.tree.domain.tree.entity.Tree;
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

    @JoinColumn(name = "tree_id")
    @ManyToOne
    private Tree tree;

    @Enumerated(EnumType.STRING)
    private CommentType commentType;
}
