package com.back.boundedContext.market.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "MARKET_MEMBER")
@NoArgsConstructor
@Getter
public class MarketMember extends ReplicaMember {
    public MarketMember(String username, String password, String nickname, int id, LocalDateTime createdAt, LocalDateTime updatedAt, int activityScore) {
        super(username, password, nickname, id, createdAt, updatedAt, activityScore);

    }
}
