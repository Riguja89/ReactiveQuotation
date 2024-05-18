package com.jaime;

import com.jaime.data.Event;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.generic.DomainEvent;
import com.jaime.serializer.JSONMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;

@Repository
public class MongoRepositoryAdapter implements DomainEventRepository {

    private final ReactiveMongoTemplate template;
    private final JSONMapper eventSerializer;

    public MongoRepositoryAdapter(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Flux<DomainEvent> findById(String aggregateId) {
        return template.find(new Query(Criteria.where("aggregateRootId").is(aggregateId)), Event.class)
                .sort(Comparator.comparing(Event::getOccurredOn))
                .map(storedEvent -> storedEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        System.out.println("estoy adentro de guardar");
        Event storedEvent = new Event();
        storedEvent.setAggregateRootId(event.getAggregateRootId());
        storedEvent.setEventBody(Event.wrapEvent(event, eventSerializer));
        storedEvent.setOccurredOn(new Date());
        storedEvent.setTypeName(event.getClass().getTypeName());
        return template.save(storedEvent)
                .map(eventStored -> eventStored.deserializeEvent(eventSerializer));
    }

    @Override
    public Flux<DomainEvent> findClientsAndReadings() {

        return template.find(new Query(
                        new Criteria().orOperator(Criteria.where("typeName").is("com.jaime.model.Quotation.events.ClientAdded"),
                                Criteria.where("typeName").is("com.jaime.model.Quotation.events.ReadingAdded"))
                ), Event.class)
                .sort(Comparator.comparing(Event::getOccurredOn))
                .map(storedEvent -> storedEvent.deserializeEvent(eventSerializer));
    }

}
