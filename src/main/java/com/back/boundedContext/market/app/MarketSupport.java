package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MarketSupport {
    private final MarketMemberRepository marketMemberRepository;

    @Transactional(readOnly = true)
    public long count() {
        return marketMemberRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findByUsername(String username) {
        return marketMemberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findById(Integer id) {
        return marketMemberRepository.findById(id);
    }
}
