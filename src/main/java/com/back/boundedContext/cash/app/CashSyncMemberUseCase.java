package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    public CashMember syncMember(MemberDto member){
        CashMember cashMember = new CashMember(
                member.getUsername(),
                "",
                member.getNickname(),
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getActivityScore()
        );
        return cashMemberRepository.save(cashMember);
    }
}
