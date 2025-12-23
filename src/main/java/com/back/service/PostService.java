package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, Member member) {
        return postRepository.save(new Post(title, content, member));
    }
}
