package com.jaime.model.Quotation.entities.Client.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ClientLastName implements ValueObject<String> {
    private final String lastName;

    private ClientLastName(String lastName) {

        if (Pattern.compile("^(?!\\\\s*$)[A-Za-záéíóúÁÉÍÓÚñÑ]{4,}$").matcher(lastName).matches()) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Apellido no válido");
        }
    }

    public static ClientLastName of(String lastName) {
        return new ClientLastName(lastName);
    }

    @Override
    public String value() {
        return lastName;
    }
}
