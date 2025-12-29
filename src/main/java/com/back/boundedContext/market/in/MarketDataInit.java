package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketMemberFacade;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.shared.post.dto.PostDto;
import com.back.shared.post.out.PostApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Slf4j
public class MarketDataInit {
    private final MarketDataInit self;
    private final MarketMemberFacade marketMemberFacade;
    private final PostApiClient postApiClient;

    public MarketDataInit(
            @Lazy MarketDataInit self,
            MarketMemberFacade marketMemberFacade,
            PostApiClient postApiClient) {
        this.self = self;
        this.marketMemberFacade = marketMemberFacade;
        this.postApiClient = postApiClient;
    }

    @Bean
    @Order(3)
    public ApplicationRunner marketDataInitApplicationRunner() {
        return args -> {
            self.makeBaseProducts();
        };
    }

    @Transactional
    public void makeBaseProducts() {
        if (marketMemberFacade.productsCount() > 0) return;

        List<PostDto> posts = postApiClient.getItems();

        PostDto post1 = posts.get(5);
        PostDto post2 = posts.get(4);
        PostDto post3 = posts.get(3);
        PostDto post4 = posts.get(2);
        PostDto post5 = posts.get(1);
        PostDto post6 = posts.get(0);

        MarketMember user1MarketMember = marketMemberFacade.findByUsername("user1").get();
        MarketMember user2MarketMember = marketMemberFacade.findByUsername("user2").get();
        MarketMember user3MarketMember = marketMemberFacade.findByUsername("user3").get();

        Product product1 = marketMemberFacade.createProduct(
                user1MarketMember,
                "Post",
                post1.getId(),
                post1.getTitle(),
                post1.getContent(),
                10_000,
                10_000
        );

        Product product2 = marketMemberFacade.createProduct(
                user1MarketMember,
                "Post",
                post2.getId(),
                post2.getTitle(),
                post2.getContent(),
                15_000,
                15_000
        );

        Product product3 = marketMemberFacade.createProduct(
                user1MarketMember,
                "Post",
                post3.getId(),
                post3.getTitle(),
                post3.getContent(),
                20_000,
                20_000
        );

        Product product4 = marketMemberFacade.createProduct(
                user2MarketMember,
                "Post",
                post4.getId(),
                post4.getTitle(),
                post4.getContent(),
                25_000,
                25_000
        );

        Product product5 = marketMemberFacade.createProduct(
                user2MarketMember,
                "Post",
                post5.getId(),
                post5.getTitle(),
                post5.getContent(),
                30_000,
                30_000
        );

        Product product6 = marketMemberFacade.createProduct(
                user3MarketMember,
                "Post",
                post6.getId(),
                post6.getTitle(),
                post6.getContent(),
                35_000,
                35_000
        );
    }
}
