package com.back.boundedContext.post.service;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, Member member) {
        member.UpdateActivityScore(3);
        return postRepository.save(new Post(title, content, member));
    }

    public Optional<Post> findByPostId(int id) {
        return postRepository.findById(id);
    }
}
