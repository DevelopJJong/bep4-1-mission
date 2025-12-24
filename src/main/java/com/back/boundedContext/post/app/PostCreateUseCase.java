package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.EventPublisher.EventPublisher;
import com.back.global.rsData.RsData;
import com.back.shared.dto.PostDto;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCreateUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public RsData<Post> create(String title, String content, Member member) {
        Post post = postRepository.save(new Post(title, content, member));

        eventPublisher.publish(
                new PostCreatedEvent(
                        new PostDto(post)
                )
        );

        return new RsData<>("201-1", "%d번 글이 생성되었습니다.".formatted(post.getId()), post);
    }

}
