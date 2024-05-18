package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.ValueObject;

public class ReadingType implements ValueObject<String> {
    private final String type;

    private ReadingType(String state) {
        try {
            this.type = ReadingTypeEnum.valueOf(state).toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de lectura no valida");
        }
    }

    public static ReadingType of(String state) {
        return new ReadingType(state);
    }

    @Override
    public String value() {
        return type;
    }
}
