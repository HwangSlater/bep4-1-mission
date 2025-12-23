package com.back.shared.post.event;

import com.back.shared.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCommentCreatedEvent {
    private PostCommentDto postCommentDto;
}
