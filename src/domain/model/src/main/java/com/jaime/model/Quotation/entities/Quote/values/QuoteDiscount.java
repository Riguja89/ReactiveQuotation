package com.jaime.model.Quotation.entities.Quote.values;

import com.jaime.model.generic.ValueObject;

public class QuoteDiscount implements ValueObject<Float> {
    private final Float increment;

    private QuoteDiscount(Float increment) {

        if (increment > 0) {
            this.increment = increment;
        } else {
            throw new IllegalArgumentException("Descuento no valido");
        }
    }

    public static QuoteDiscount of(Float discount) {
        return new QuoteDiscount(discount);
    }

    @Override
    public Float value() {
        return increment;
    }
}
