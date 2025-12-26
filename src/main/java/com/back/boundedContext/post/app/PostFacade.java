package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostSupport postSupport;
    private final PostCreateUseCase postCreateUseCase;
    private final PostSyncMemberUseCase postSyncMemberUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postSupport.count();
    }

    @Transactional
    public RsData<Post> create(String title, String content, PostMember member) {
        return postCreateUseCase.create(title, content, member);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findByPostId(int id) {
        return postSupport.findByPostId(id);
    }

    @Transactional(readOnly = true)
    public Optional<PostMember> findByUsername(String username){
        return postSupport.findByUsername(username);
    }

    @Transactional
    public PostMember syncMember(MemberDto member){
        return postSyncMemberUseCase.syncMember(member);
    }
}
