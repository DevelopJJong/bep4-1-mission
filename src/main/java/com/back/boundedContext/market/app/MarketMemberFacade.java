package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
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
}
