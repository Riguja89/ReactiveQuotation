package com.jaime.model.Quotation.values;

import com.jaime.model.generic.Identity;

public class QuotationId extends Identity {
    public QuotationId() {
    }

    public QuotationId(String uuid) {
        super(uuid);
    }

    public static QuotationId of(String uuid) {
        return new QuotationId(uuid);
    }
}
