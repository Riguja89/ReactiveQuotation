package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.ValueObject;


public class ReadingAmount implements ValueObject<Integer> {
    private final Integer amount;

    private ReadingAmount(Integer amount) {

        if (amount >= 0 && amount < 600) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Cantidad de libro fuera de rango");
        }
    }

    public static ReadingAmount of(Integer amount) {
        return new ReadingAmount(amount);
    }

    @Override
    public Integer value() {
        return amount;
    }
}
