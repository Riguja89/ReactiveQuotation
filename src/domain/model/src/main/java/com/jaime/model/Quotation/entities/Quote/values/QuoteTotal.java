package com.jaime.model.Quotation.entities.Quote.values;

import com.jaime.model.generic.ValueObject;

public class QuoteTotal implements ValueObject<Float> {

    private final Float total;

    private QuoteTotal(Float total) {

        if (total > 0) {
            this.total = total;
        } else {
            throw new IllegalArgumentException("Total no valido");
        }
    }

    public static QuoteTotal of(Float total) {
        return new QuoteTotal(total);
    }

    @Override
    public Float value() {
        return total;
    }
}
