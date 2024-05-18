package com.jaime.model.Quotation.entities.Client.values;

import com.jaime.model.generic.ValueObject;

import java.util.regex.Pattern;

public class ClientName implements ValueObject<String> {
    private final String name;

    private ClientName(String name) {

        if (Pattern.compile("^(?!\\\\s*$)[A-Za-záéíóúÁÉÍÓÚñÑ]{4,}$").matcher(name).matches()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Nombre no válido");
        }
    }

    public static ClientName of(String name) {
        return new ClientName(name);
    }

    @Override
    public String value() {
        return name;
    }
}


