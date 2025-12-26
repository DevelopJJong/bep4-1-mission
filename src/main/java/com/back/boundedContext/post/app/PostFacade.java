package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostCreateUseCase postCreateUseCase;
    private final PostMemberRepository postMemberRepository;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public RsData<Post> create(String title, String content, PostMember member) {
        return postCreateUseCase.create(title, content, member);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findByPostId(int id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<PostMember> findByUsername(String username){
        return postMemberRepository.findByUsername(username);
    }

    public PostMember syncMember(MemberDto member){
        PostMember postMember = new PostMember(
                member.getUsername(),
                "",
                member.getNickname(),
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getActivityScore()
        );
        return postMemberRepository.save(postMember);
    }
}
