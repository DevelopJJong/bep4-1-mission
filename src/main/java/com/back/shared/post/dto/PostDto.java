package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;



@AllArgsConstructor(
        onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
) // final 때고 setter 쓰는 대신 이 코드 사용
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
