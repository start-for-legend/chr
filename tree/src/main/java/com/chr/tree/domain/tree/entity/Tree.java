package com.chr.tree.domain.tree.entity;

import com.chr.tree.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tree_id")
    private Long treeId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
