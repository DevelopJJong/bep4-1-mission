package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashMemberFacade {
    private final CashMemberRepository cashMemberRepository;

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

    public Optional<CashMember> findByUsername(String username){
        return cashMemberRepository.findByUsername(username);
    }
}
