package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.global.rsData.RsData;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shared.market.dto.MarketMemberDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketMemberFacade {
    private final MarketSupport marketSupport;
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;
    private final MarketCreateProductUseCase marketCreateProductUseCase;
    private final MarketCreateCartUseCase marketCreateCartUseCase;
    private final MarketCreateOrderUseCase marketCreateOrderUseCase;
    private final MarketCompleteOrderPaymentUseCase marketCompleteOrderPaymentUseCase;
    private final MarketCancelOrderRequestPaymentUseCase marketCancelOrderRequestPaymentUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return marketSupport.count();
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findByUsername(String username){
        return marketSupport.findByUsername(username);
    }

    @Transactional
    public MarketMember syncMember(MemberDto member){
        return marketSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public Product createProduct(MarketMember seller, String sourceTypeCode, int sourceId, String name, String description, long price, long salePrice){
        return marketCreateProductUseCase.createProduct(seller, sourceTypeCode, sourceId, name, description, price, salePrice);
    }

    @Transactional(readOnly = true)
    public long productsCount(){
        return marketSupport.countProducts();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(int id){
        return marketSupport.findProductById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Cart> findCartByBuyer(MarketMember buyer){
        return marketSupport.findCartByBuyer(buyer);
    }

    @Transactional
    public RsData<Cart> createCart(MarketMemberDto buyer) {
        return marketCreateCartUseCase.createCart(buyer);
    }

    public long ordersCount() {
        return marketSupport.countOrders();
    }

    public RsData<Order> createOrder(Cart cart) {
        return marketCreateOrderUseCase.createOrder(cart);
    }


    @Transactional(readOnly = true)
    public Optional<Order> findOrderById(int id) {
        return marketSupport.findOrderById(id);
    }

    @Transactional
    public void requestPayment(int orderId, long pgPaymentAmount) {
        Order order = marketSupport.findOrderById(orderId).get();
        order.requestPayment(pgPaymentAmount);
    }

    @Transactional
    public void handle(CashOrderPaymentSucceededEvent event) {
        marketCompleteOrderPaymentUseCase.handle(event);
    }

    @Transactional
    public void handle(CashOrderPaymentFailedEvent event) {
        marketCancelOrderRequestPaymentUseCase.handle(event);
    }
}
