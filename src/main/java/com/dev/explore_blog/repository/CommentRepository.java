package com.dev.explore_blog.repository;

import com.dev.explore_blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Set<Comment> findAllByPostId(Long postId);
}
