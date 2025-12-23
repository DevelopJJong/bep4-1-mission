package com.back.initData;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.service.MemberService;
import com.back.service.PostService;
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
    private final MemberService memberService;
    private final PostService postService;

    public DataInit(@Lazy DataInit self, MemberService memberService, PostService postService) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePost();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        // 이미 존재하면 return
        if (memberService.count() > 0) return;

        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePost(){
        // 이미 존재하면 return
        if (postService.count() > 0) return;

        Post post1 = postService.create("1번", "1", memberService.findByUsername("user1").get());
        Post post2 = postService.create("2번", "2", memberService.findByUsername("user1").get());
        Post post3 = postService.create("3번", "3", memberService.findByUsername("user1").get());
        Post post4 = postService.create("4번", "4", memberService.findByUsername("user2").get());
        Post post5 = postService.create("5번", "5", memberService.findByUsername("user2").get());
        Post post6 = postService.create("6번", "6", memberService.findByUsername("user3").get());
    }
}