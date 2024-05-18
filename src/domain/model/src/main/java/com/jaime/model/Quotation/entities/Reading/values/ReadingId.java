package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.Identity;

public class ReadingId extends Identity {
    public ReadingId() {
        super();
    }

    public ReadingId(String uuid) {
        super(uuid);
    }

    public static ReadingId of(String uuid) {
        return new ReadingId(uuid);
    }
}
