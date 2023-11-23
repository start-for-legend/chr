package com.chr.tree.domain.comment.repository;

import com.chr.tree.domain.comment.entity.Comment;
import com.chr.tree.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserContains(User user);
}
