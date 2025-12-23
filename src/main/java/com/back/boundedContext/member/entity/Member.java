package com.back.boundedContext.member.entity;

import com.back.boundedContext.post.entity.Comment;
import com.back.boundedContext.post.entity.Post;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseIdAndTime {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;

    public Member(String username, String password, String nickname, int activityScore) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.activityScore = activityScore;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();

    public int updateActivityScore(int score) {
        return this.activityScore += score;
    }
}