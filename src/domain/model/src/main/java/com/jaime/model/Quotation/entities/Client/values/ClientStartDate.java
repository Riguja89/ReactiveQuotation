package com.jaime.model.Quotation.entities.Client.values;

import com.jaime.model.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientStartDate implements ValueObject<LocalDate> {
    private final LocalDate startDate;

    private ClientStartDate(String startDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        try {
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            this.startDate = localDate;
        } catch (
                DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha no valido, debe ser yyyy-M-d");
        }
    }

    public static ClientStartDate of(String name) {
        return new ClientStartDate(name);
    }

    @Override
    public LocalDate value() {
        return startDate;
    }

}
