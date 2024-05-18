package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class QuotationCreated extends DomainEvent {

    public QuotationCreated() {
        super(EventsEnum.QUOTATION_CREATED.toString());
    }
}
