package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.EventPublisher.EventPublisher;
import com.back.shared.dto.PostDto;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, Member member) {
        Post post = postRepository.save(new Post(title, content, member));

        eventPublisher.publish(
                new PostCreatedEvent(
                        new PostDto(post)
                )
        );

        return post;
    }

    public Optional<Post> findByPostId(int id) {
        return postRepository.findById(id);
    }
}
