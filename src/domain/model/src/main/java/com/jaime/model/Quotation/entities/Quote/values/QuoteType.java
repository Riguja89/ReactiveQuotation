package com.jaime.model.Quotation.entities.Quote.values;

import com.jaime.model.generic.ValueObject;

public class QuoteType implements ValueObject<String> {
    private final String type;

    private QuoteType(String type) {
        try {
            this.type = QuoteTypeEnum.valueOf(type).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de cotización no valido");
        }
    }

    public static QuoteType of(String type) {
        return new QuoteType(type);
    }

    @Override
    public String value() {
        return type;
    }
}
