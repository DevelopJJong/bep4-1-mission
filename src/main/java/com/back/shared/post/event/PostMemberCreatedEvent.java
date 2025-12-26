package com.back.shared.post.event;

import com.back.shared.post.dto.PostMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostMemberCreatedEvent {
    private final PostMemberDto member;
}
