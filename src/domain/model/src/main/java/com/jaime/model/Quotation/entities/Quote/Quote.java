package com.jaime.model.Quotation.entities.Quote;

import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.values.*;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.generic.Entity;

import java.util.List;

public class Quote extends Entity<QuoteId> {
    private QuoteDiscount discount;

    private QuoteTotal total;
    private QuoteType type;
    private Client client;
    private List<Reading> readings;

    public Quote(QuoteId id) {
        super(id);
    }

    public Quote(Client client, List<Reading> readings) {
        super(new QuoteId());
        this.client = client;
        this.readings = readings;
    }


    public Float discount() {
        return discount.value();
    }
    
    public Float total() {
        return total.value();
    }

    public String type() {
        return type.value();
    }

    public Client client() {
        return client;
    }

    public List<Reading> readings() {
        return readings;
    }


}
