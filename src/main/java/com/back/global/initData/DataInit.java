package com.back.global.initData;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.app.PostService;
import com.back.boundedContext.post.domain.Post;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberFacade memberFacade;
    private final PostService postService;


    public DataInit(@Lazy DataInit self, MemberFacade memberFacade, PostService postService) {
        this.self = self;
        this.memberFacade = memberFacade;
        this.postService = postService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePost();
            self.makeBaseComment();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        // 이미 존재하면 return
        if (memberFacade.count() > 0) return;

        Member systemMember = memberFacade.join("system", "1234", "시스템");
        Member holdingMember = memberFacade.join("holding", "1234", "홀딩");
        Member adminMember = memberFacade.join("admin", "1234", "관리자");
        Member user1Member = memberFacade.join("user1", "1234", "유저1");
        Member user2Member = memberFacade.join("user2", "1234", "유저2");
        Member user3Member = memberFacade.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePost(){
        // 이미 존재하면 return
        if (postService.count() > 0) return;

        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        Post post1 = postService.create("1번", "1", user1Member);
        Post post2 = postService.create("2번", "2", user1Member);
        Post post3 = postService.create("3번", "3", user1Member);
        Post post4 = postService.create("4번", "4", user2Member);
        Post post5 = postService.create("5번", "5", user2Member);
        Post post6 = postService.create("6번", "6", user3Member);
    }

    @Transactional
    public void makeBaseComment(){

        Post post1 = postService.findByPostId(1).get();
        Post post2 = postService.findByPostId(2).get();
        Post post3 = postService.findByPostId(3).get();
        Post post4 = postService.findByPostId(4).get();
        Post post5 = postService.findByPostId(5).get();
        Post post6 = postService.findByPostId(6).get();

        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        if (post1.hasComments()) return;

        post1.addComment(user1Member, "댓글1");
        post1.addComment(user2Member, "댓글2");
        post1.addComment(user3Member, "댓글3");

        post2.addComment(user2Member, "댓글4");
        post2.addComment(user2Member, "댓글5");

        post3.addComment(user3Member, "댓글6");
        post3.addComment(user3Member, "댓글7");

        post4.addComment(user1Member, "댓글8");
    }
}