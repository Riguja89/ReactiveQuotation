package com.jaime.model.generic;

import java.util.List;

public class AggregateRoot<I extends Identity> extends Entity<I> {

    private final ChangeEventSubscriber changeEventSubscriber;

    public AggregateRoot(I id) {
        super(id);
        changeEventSubscriber = new ChangeEventSubscriber();
    }

    protected ChangeApply appendChange(DomainEvent event) {
        var nameClass = identity().getClass().getSimpleName();
        var aggregate = nameClass.replaceAll("Identity|Id|ID", "").toLowerCase();
        event.setAggregateName(aggregate);
        event.setAggregateRoot(identity().value());
        return changeEventSubscriber.appendChange(event);
    }

    public List<DomainEvent> getUncommitedChanges() {
        return List.copyOf(changeEventSubscriber.changes());
    }

    public final void subscribe(EventChange eventChange) {
        changeEventSubscriber.subscribe(eventChange);
    }

    public void markChangeAsCommitted() {
        changeEventSubscriber.changes().clear();
    }

    protected void applyEvent(DomainEvent event) {
        changeEventSubscriber.applyEvent(event);
    }
}
