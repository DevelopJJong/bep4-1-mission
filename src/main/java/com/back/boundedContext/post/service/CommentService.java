package com.back.boundedContext.post.service;

import com.back.boundedContext.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public long count() {
        return commentRepository.count();
    }
}
