package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostCreateUseCase postCreateUseCase;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, Member member) {
        return  postCreateUseCase.create(title, content, member);
    }

    public Optional<Post> findByPostId(int id) {
        return postRepository.findById(id);
    }
}
