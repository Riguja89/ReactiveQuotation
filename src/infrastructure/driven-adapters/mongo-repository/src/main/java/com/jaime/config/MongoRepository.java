package com.jaime.config;


import com.jaime.data.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface MongoRepository extends ReactiveMongoRepository<Event, String> {
    Mono<Event> getByTypeName(String typeName);
}
