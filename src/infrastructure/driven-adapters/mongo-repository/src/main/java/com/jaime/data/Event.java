package com.jaime.data;

import com.jaime.model.generic.DomainEvent;
import com.jaime.serializer.JSONMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Event {


    private String aggregateRootId;
    private String eventBody;
    private Date occurredOn;
    private String typeName;

    public Event() {
    }

    public Event(String aggregateRootId, String eventBody, Date occurredOn, String typeName) {
        this.aggregateRootId = aggregateRootId;
        this.eventBody = eventBody;
        this.occurredOn = occurredOn;
        this.typeName = typeName;
    }

    public static String wrapEvent(DomainEvent domainEvent, JSONMapper eventSerializer) {
        return eventSerializer.writeToJson(domainEvent);
    }


    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public Date getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public DomainEvent deserializeEvent(JSONMapper eventSerializer) {
        try {
            return (DomainEvent) eventSerializer
                    .readFromJson(this.getEventBody(), Class.forName(this.getTypeName()));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public interface EventSerializer {
        <T extends DomainEvent> T deserialize(String aSerialization, final Class<?> aType);

        String serialize(DomainEvent object);
    }
}
