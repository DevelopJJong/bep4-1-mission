package com.back.boundedContext.payout.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.payout.dto.PayoutMemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "PAYOUT_MEMBER")
@NoArgsConstructor
@Getter
public class PayoutMember extends ReplicaMember{
    public PayoutMember(String username, String password, String nickname, int id, LocalDateTime createdAt, LocalDateTime updatedAt, int activityScore) {
        super(username, password, nickname, id, createdAt, updatedAt, activityScore);
    }

    public PayoutMemberDto toDto() {
        return new PayoutMemberDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                getUsername(),
                getNickname(),
                getActivityScore()
        );
    }
}
