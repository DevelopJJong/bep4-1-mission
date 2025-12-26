package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "POST_COMMENT")
public class Comment extends BaseIdAndTime {
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostMember member;

    public Comment(String content, Post post, PostMember member) {
        this.content = content;
        this.post = post;
        this.member = member;
    }
}
