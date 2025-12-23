package com.back.shared.dto;

import com.back.boundedContext.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCommentDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int postId;
    private final int memberId;
    private final String memberName;
    private final String content;

    public PostCommentDto(Comment comment) {
        this(
                comment.getId(),
                comment.getCreateDate(),
                comment.getModifyDate(),
                comment.getPost().getId(),
                comment.getMember().getId(),
                comment.getMember().getNickname(),
                comment.getContent()
        );
    }
}