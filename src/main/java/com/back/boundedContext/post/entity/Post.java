package com.back.boundedContext.post.entity;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();


    public Comment addComment(Member member, String content) {
        Comment comments = new Comment(content, this, member);

        comment.add(comments);

        member.UpdateActivityScore(1);
        return comments;
    }
}
