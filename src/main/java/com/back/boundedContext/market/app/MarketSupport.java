package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.boundedContext.market.out.CartRepository;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.boundedContext.market.out.OrderRepository;
import com.back.boundedContext.market.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MarketSupport {
    private final MarketMemberRepository marketMemberRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public long count() {
        return marketMemberRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findByUsername(String username) {
        return marketMemberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public long countProducts(){
        return productRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findById(Integer id) {
        return marketMemberRepository.findById(id);
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Cart> findCartByBuyer(MarketMember buyer) {
        return cartRepository.findByBuyer(buyer);
    }

    public long countOrders(){
        return orderRepository.count();
    }

    public Optional<Order> findOrderById(int id) {
        return orderRepository.findById(id);
    }
}
