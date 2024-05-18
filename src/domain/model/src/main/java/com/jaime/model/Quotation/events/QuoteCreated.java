package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class QuoteCreated extends DomainEvent {
    private String quoteId;

    public QuoteCreated(String quoteId) {
        super(EventsEnum.QUOTE_CREATED.toString());
        this.quoteId = quoteId;
    }

    public QuoteCreated() {
        super(EventsEnum.QUOTE_CREATED.toString());
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }
}
