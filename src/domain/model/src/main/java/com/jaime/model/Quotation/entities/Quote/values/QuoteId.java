package com.jaime.model.Quotation.entities.Quote.values;

import com.jaime.model.generic.Identity;

public class QuoteId extends Identity {
    public QuoteId() {
        super();
    }

    private QuoteId(String uuid) {
        super(uuid);
    }

    public static QuoteId of(String uuid) {
        return new QuoteId(uuid);
    }
}
