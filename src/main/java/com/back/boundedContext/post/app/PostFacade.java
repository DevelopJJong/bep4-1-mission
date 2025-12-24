package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostCreateUseCase postCreateUseCase;

    @Transactional
    public long count() {
        return postRepository.count();
    }

    @Transactional
    public RsData<Post> create(String title, String content, Member member) {
        return postCreateUseCase.create(title, content, member);
    }

    @Transactional
    public Optional<Post> findByPostId(int id) {
        return postRepository.findById(id);
    }
}
