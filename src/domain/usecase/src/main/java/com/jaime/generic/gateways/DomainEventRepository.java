package com.jaime.generic.gateways;

import com.jaime.model.generic.DomainEvent;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DomainEventRepository {

    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Flux<DomainEvent> findClientsAndReadings();
}
