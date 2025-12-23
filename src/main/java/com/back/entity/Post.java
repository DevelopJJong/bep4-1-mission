package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @Column(unique = true)
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
