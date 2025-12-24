package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Comment extends BaseIdAndTime {
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Comment(String content, Post post, Member member) {
        this.content = content;
        this.post = post;
        this.member = member;
    }
}
