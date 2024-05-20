package com.jaime.helpers;

import com.jaime.model.Quotation.entities.Reading.Reading;

import java.util.ArrayList;
import java.util.List;

public class HelpersUseCase {

    public static ResponseItem calculateItem(Reading reading, Integer readingsAmount, Integer seniority){
        Float mayorDiscount = 0.0f;
        Float finalPrice = reading.calculatePrice();
        Float discount = 0f;
        Float discountFinal = 0f;
        Float discountMayor;

        if (readingsAmount > 10) {
            mayorDiscount = 0.15f * (readingsAmount);
            discount = -finalPrice * 0.02f;
        }

        finalPrice = finalPrice - discount;
        discountFinal+=discount;
        discount = (seniority / 100f) * finalPrice;
        finalPrice = finalPrice - discount;
        discountFinal+=discount;
        finalPrice = finalPrice * reading.amount();
        discount = discount * reading.amount();
        discountFinal+=discount;
        discountMayor = finalPrice * (mayorDiscount / 100f);
        discount += discountMayor;
        discountFinal+=discount;
        finalPrice -= discount;

        return new ResponseItem(finalPrice,discountFinal);
    }
}
