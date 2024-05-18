package com.jaime.generic.gateways;


import com.jaime.model.generic.DomainEvent;
import org.springframework.stereotype.Component;


@Component
public interface EventBus {

    void publish(DomainEvent event);
    void publishError(Throwable errorEvent);
}
