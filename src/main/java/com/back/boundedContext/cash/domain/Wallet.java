package com.back.boundedContext.cash.domain;

import com.back.global.jpa.entity.BaseEntity;
import com.back.global.jpa.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;


@Entity
@Table(name = "CASH_WALLET")
@NoArgsConstructor
@Getter
public class Wallet extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder; // holder == 소유자

    private long balance;

    @OneToMany(mappedBy = "wallet", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<CashLog> cashLogs = new ArrayList<>();

    public Wallet(CashMember holder){
        super(holder.getId());
        this.holder = holder;
    }

    public boolean hasBalance(){
        return balance > 0;
    }

    /*
     **  입금
     */

    public void deposit(long amount, CashLog.EventType eventType, String relTypeCode, int relId){
        balance += amount;

        addCashLog(amount, eventType, relTypeCode, relId);
    }

    public void deposit(long amount, CashLog.EventType eventType, BaseEntity rel){
        deposit(amount, eventType, rel.getModelTypeCode(), rel.getId());
    }

    public void deposit(int amount, CashLog.EventType eventType){
        deposit(amount, eventType, holder);
    }

    /*
     **  출금
     */
    public void withdraw(long amount, CashLog.EventType eventType, String relTypeCode, int relId) {
        balance -= amount;

        addCashLog(-amount, eventType, relTypeCode, relId);
    }

    public void withdraw(long amount, CashLog.EventType eventType, BaseEntity rel) {
        withdraw(amount, eventType, rel.getModelTypeCode(), rel.getId());
    }

    public void withdraw(long amount, CashLog.EventType eventType) {
        withdraw(amount, eventType, holder);
    }


    private CashLog addCashLog(long amount, CashLog.EventType eventType, String relTypeCode, int relId){
        CashLog cashLog = new CashLog(
                eventType,
                relTypeCode,
                relId,
                holder,
                this,
                amount,
                balance
        );

        cashLogs.add(cashLog);

        return cashLog;
    }
}
