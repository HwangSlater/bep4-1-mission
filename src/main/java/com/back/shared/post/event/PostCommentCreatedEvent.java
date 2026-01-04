package com.back.shared.post.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.post.dto.PostCommentDto;
import lombok.Getter;

@Getter
public class PostCommentCreatedEvent extends BaseEvent {
    PostCommentDto postCommentDto;

    public PostCommentCreatedEvent(PostCommentDto postCommentDto) {
        super(ServiceName.POST_SERVICE.value());

        this.postCommentDto = postCommentDto;
    }
}
