package com.back.global.eventPublisher;

public interface EventPublisher {
    /*private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(Object event) {
        applicationEventPublisher.publishEvent(event);
    }*/

    void publish(BaseEvent event);
}
