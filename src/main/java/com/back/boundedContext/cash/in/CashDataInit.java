package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashMemberFacade;
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
    private CashMemberFacade cashMemberFacade;

    public CashDataInit(@Lazy CashDataInit self, CashMemberFacade cashMemberFacade){
        this.self = self;
        this.cashMemberFacade = cashMemberFacade;
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

        CashMember member1 = cashMemberFacade.findByUsername("user1").get();
        CashMember member2 = cashMemberFacade.findByUsername("user2").get();

        Wallet wallet1 = cashMemberFacade.findWalletByHolder(member1).get();

        if (wallet1.hasBalance()) return;

        wallet1.deposit(150_000, CashLog.EventType.충전__무통장입금);
        wallet1.deposit(100_000, CashLog.EventType.충전__무통장입금);
        wallet1.deposit(50_000, CashLog.EventType.충전__무통장입금);

        Wallet wallet2 = cashMemberFacade.findWalletByHolder(member2).get();

        if (wallet2.hasBalance()) return;

        wallet2.deposit(150_000, CashLog.EventType.충전__무통장입금);
    }
}
