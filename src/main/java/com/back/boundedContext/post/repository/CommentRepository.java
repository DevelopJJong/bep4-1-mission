package com.back.boundedContext.post.repository;

import com.back.boundedContext.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
