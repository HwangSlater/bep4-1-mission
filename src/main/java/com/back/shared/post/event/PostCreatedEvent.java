package com.back.shared.post.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.post.dto.PostDto;
import lombok.Getter;

@Getter
public class PostCreatedEvent extends BaseEvent {
    PostDto postDto;

    public PostCreatedEvent(PostDto postDto) {
        super(ServiceName.POST_SERVICE.value());

        this.postDto = postDto;
    }
}
