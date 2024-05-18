package com.jaime.model.Quotation.entities.Client.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ClientEmail implements ValueObject<String> {
    private final String email;

    private ClientEmail(String email) {

        if (Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email no válido");
        }
    }

    public static ClientEmail of(String email) {
        return new ClientEmail(email);
    }

    @Override
    public String value() {
        return email;
    }
}
