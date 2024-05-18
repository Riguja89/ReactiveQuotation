package com.jaime.model.Quotation.entities.Reading.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ReadingAuthor implements ValueObject<String> {
    private final String author;

    private ReadingAuthor(String author) {

        if (Pattern.compile("^[A-Za-z0-9\\s.,'-]{1,50}$").matcher(author).matches()) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Nombre no válido");
        }
    }

    public static ReadingAuthor of(String author) {
        return new ReadingAuthor(author);
    }

    @Override
    public String value() {
        return author;
    }
}
