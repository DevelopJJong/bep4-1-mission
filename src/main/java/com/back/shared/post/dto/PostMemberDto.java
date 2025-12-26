package com.back.shared.post.dto;

import com.back.boundedContext.cash.domain.CashMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostMemberDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String nickname;
    private final int activityScore;

    public PostMemberDto(CashMember member) {
        this(
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
