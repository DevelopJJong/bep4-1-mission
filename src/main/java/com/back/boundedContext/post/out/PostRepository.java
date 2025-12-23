package com.back.boundedContext.post.out;

import com.back.boundedContext.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(int id);
}
