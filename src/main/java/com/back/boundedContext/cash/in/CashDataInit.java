package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
public class CashDataInit {
    private final CashDataInit self;
    private CashFacade cashFacade;

    public CashDataInit(@Lazy CashDataInit self, CashFacade cashFacade){
        this.self = self;
        this.cashFacade = cashFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner cashDataInitApplicationRunner() {
        return args -> {
            self.makeBaseCash();
        };
    }

    @Transactional
    public void makeBaseCash(){

        CashMember member1 = cashFacade.findByUsername("user1").get();
        CashMember member2 = cashFacade.findByUsername("user2").get();

        Wallet wallet1 = cashFacade.findWalletByHolder(member1).get();

        if (wallet1.hasBalance()) return;

        wallet1.deposit(150_000, CashLog.EventType.충전__무통장입금);
        wallet1.deposit(100_000, CashLog.EventType.충전__무통장입금);
        wallet1.deposit(50_000, CashLog.EventType.충전__무통장입금);

        Wallet wallet2 = cashFacade.findWalletByHolder(member2).get();

        if (wallet2.hasBalance()) return;

        wallet2.deposit(150_000, CashLog.EventType.충전__무통장입금);
    }
}
