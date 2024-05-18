package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ReadingTitle implements ValueObject<String> {

    private final String title;

    private ReadingTitle(String title) {

       // if (Pattern.compile("^[A-Za-z0-9\\s.,'-]{1,50}$").matcher(title).matches()) {
            this.title = title;
       // } else {
       //     throw new IllegalArgumentException("Titulo no válido");
       // }
    }

    public static ReadingTitle of(String title) {
        return new ReadingTitle(title);
    }

    @Override
    public String value() {
        return title;
    }
}
