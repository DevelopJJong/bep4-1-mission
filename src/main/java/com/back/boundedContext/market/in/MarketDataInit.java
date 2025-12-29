package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketMemberFacade;
import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.shared.post.dto.PostDto;
import com.back.shared.post.out.PostApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
    @org.springframework.core.annotation.Order(3)
    public ApplicationRunner marketDataInitApplicationRunner() {
        return args -> {
            self.makeBaseProducts();
            self.makeBaseCartItems();
            self.makeBaseOrders();
            self.makeBasePaidOrders();
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

    @Transactional
    public void makeBaseCartItems() {
        MarketMember user1Member = marketMemberFacade.findByUsername("user1").get();
        MarketMember user2Member = marketMemberFacade.findByUsername("user2").get();
        MarketMember user3Member = marketMemberFacade.findByUsername("user3").get();

        Cart cart1 = marketMemberFacade.findCartByBuyer(user1Member).get();
        Cart cart2 = marketMemberFacade.findCartByBuyer(user2Member).get();
        Cart cart3 = marketMemberFacade.findCartByBuyer(user3Member).get();

        Product product1 = marketMemberFacade.findProductById(1).get();
        Product product2 = marketMemberFacade.findProductById(2).get();
        Product product3 = marketMemberFacade.findProductById(3).get();
        Product product4 = marketMemberFacade.findProductById(4).get();
        Product product5 = marketMemberFacade.findProductById(5).get();
        Product product6 = marketMemberFacade.findProductById(6).get();

        if (cart1.hasItems()) return;

        cart1.addItem(product1);
        cart1.addItem(product2);
        cart1.addItem(product3);
        cart1.addItem(product4);

        cart2.addItem(product1);
        cart2.addItem(product2);
        cart2.addItem(product3);

        cart3.addItem(product1);
        cart3.addItem(product2);
    }

    @Transactional
    public void makeBaseOrders() {
        if (marketMemberFacade.ordersCount() > 0) return;

        MarketMember user1Member = marketMemberFacade.findByUsername("user1").get();
        MarketMember user2Member = marketMemberFacade.findByUsername("user2").get();
        MarketMember user3Member = marketMemberFacade.findByUsername("user3").get();

        Cart cart1 = marketMemberFacade.findCartByBuyer(user1Member).get();
        Cart cart2 = marketMemberFacade.findCartByBuyer(user2Member).get();
        Cart cart3 = marketMemberFacade.findCartByBuyer(user3Member).get();

        Order order1 = marketMemberFacade.createOrder(cart1).getData();
        Order order2 = marketMemberFacade.createOrder(cart2).getData();
        Order order3 = marketMemberFacade.createOrder(cart3).getData();

        // 주문 생성 때문에 cart1이 비어있기 때문에 다시 아이템 추가
        Product product1 = marketMemberFacade.findProductById(1).get();
        Product product2 = marketMemberFacade.findProductById(2).get();
        Product product3 = marketMemberFacade.findProductById(3).get();
        Product product4 = marketMemberFacade.findProductById(4).get();
        Product product5 = marketMemberFacade.findProductById(5).get();
        Product product6 = marketMemberFacade.findProductById(6).get();

        cart1.addItem(product1);
        cart1.addItem(product2);
        cart1.addItem(product3);
        cart1.addItem(product4);
    }

    @Transactional
    public void makeBasePaidOrders() {
        Order order1 = marketMemberFacade.findOrderById(1).get();

        if (order1.isPaid()) return;

        marketMemberFacade.requestPayment(order1, 0);
    }
}
