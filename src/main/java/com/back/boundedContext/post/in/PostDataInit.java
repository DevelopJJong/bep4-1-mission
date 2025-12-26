package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
public class PostDataInit {
    private final PostDataInit self;
    private final PostFacade postFacade;

    public PostDataInit(@Lazy PostDataInit self, PostFacade postFacade) {
        this.self = self;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postDataInitApplicationRunner() {
        return args -> {
            self.makeBasePost();
            self.makeBaseComment();
        };
    }

    @Transactional
    public void makeBasePost(){
        // 이미 존재하면 return
        if (postFacade.count() > 0) return;

        PostMember user1Member = postFacade.findByUsername("user1").get();
        PostMember user2Member = postFacade.findByUsername("user2").get();
        PostMember user3Member = postFacade.findByUsername("user3").get();

        RsData<Post> post1 = postFacade.create("1번", "1", user1Member);
        log.debug(post1.getMsg());

        RsData<Post> post2 = postFacade.create("2번", "2", user1Member);
        log.debug(post2.getMsg());

        RsData<Post> post3 = postFacade.create("3번", "3", user1Member);
        log.debug(post3.getMsg());

        RsData<Post> post4 = postFacade.create("4번", "4", user2Member);
        log.debug(post4.getMsg());

        RsData<Post> post5 = postFacade.create("5번", "5", user2Member);
        log.debug(post5.getMsg());

        RsData<Post> post6 = postFacade.create("6번", "6", user3Member);
        log.debug(post6.getMsg());
    }

    @Transactional
    public void makeBaseComment(){

        Post post1 = postFacade.findByPostId(1).get();
        Post post2 = postFacade.findByPostId(2).get();
        Post post3 = postFacade.findByPostId(3).get();
        Post post4 = postFacade.findByPostId(4).get();
        Post post5 = postFacade.findByPostId(5).get();
        Post post6 = postFacade.findByPostId(6).get();

        PostMember user1Member = postFacade.findByUsername("user1").get();
        PostMember user2Member = postFacade.findByUsername("user2").get();
        PostMember user3Member = postFacade.findByUsername("user3").get();

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
