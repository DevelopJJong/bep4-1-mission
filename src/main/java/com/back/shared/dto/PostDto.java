package com.back.shared.dto;

import com.back.boundedContext.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class PostDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int memberId;
    private final String memberName;
    private final String title;
    private final String content;

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getMember().getId(),
                post.getMember().getNickname(),
                post.getTitle(),
                post.getContent()
        );
    }
}
