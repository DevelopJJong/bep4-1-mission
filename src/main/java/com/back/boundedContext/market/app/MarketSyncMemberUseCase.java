package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarketSyncMemberUseCase {
    private final MarketMemberRepository marketMemberRepository;

    public MarketMember syncMember(MemberDto member){
        MarketMember marketMember = marketMemberRepository.save(
                new MarketMember(
                        member.getUsername(),
                        "",
                        member.getNickname(),
                        member.getId(),
                        member.getCreateDate(),
                        member.getModifyDate(),
                        member.getActivityScore()
                )
        );

        return marketMember;
    }
}
