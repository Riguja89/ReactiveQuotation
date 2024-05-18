package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ReadingOriginalPrice implements ValueObject<Long> {
    private final Long originalPrice;

    private ReadingOriginalPrice(Long originalPrice) {

        if (originalPrice > 0 && originalPrice < 5000000) {
            this.originalPrice = originalPrice;
        } else {
            throw new IllegalArgumentException("El precio no esta en el rango");
        }
    }

    public static ReadingOriginalPrice of(Long originalPrice) {
        return new ReadingOriginalPrice(originalPrice);
    }

    @Override
    public Long value() {
        return originalPrice;
    }
}
