package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashMemberFacade;
import com.back.boundedContext.cash.domain.CashMember;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class CashMemberEventListener {
    private final CashMemberFacade cashMemberFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        CashMember member = cashMemberFacade.syncMember(event.getMember());

        cashMemberFacade.createWallet(member);
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        cashMemberFacade.syncMember(event.getMember());
    }
}
