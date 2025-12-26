package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.cash.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final WalletRepository walletRepository;
    private final CashMemberRepository cashMemberRepository;

    @Transactional
    public Wallet createWallet(CashMemberDto holder){
        CashMember member = cashMemberRepository.getReferenceById(holder.getId()); // getReferenceById 사용하면 쿼리를 날리지 않는다. 최적화에 좋음
        Wallet wallet = new Wallet(member);

        return walletRepository.save(wallet);
    }
}
