package com.jaime.model.Quotation.entities.Client;

import com.jaime.model.Quotation.entities.Client.values.*;
import com.jaime.model.generic.Entity;

import java.time.LocalDate;
import java.time.Period;

public class Client extends Entity<ClientId> {
    private ClientName name;
    private ClientLastName lastName;
    private ClientEmail email;
    private ClientStartDate startDate;

    public Client(ClientId id) {
        super(id);
    }

    private Client(ClientId clientId, ClientName name, ClientLastName lastName, ClientEmail email, ClientStartDate startDate) {
        super(clientId);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
    }

    public static Client from(String clientId, String name, String lastName, String email, LocalDate startDate) {
        return new Client(ClientId.of(clientId), ClientName.of(name), ClientLastName.of(lastName), ClientEmail.of(email), ClientStartDate.of(startDate.toString()));
    }

    public String getName() {
        return name.value();
    }

    public String getLastName() {
        return lastName.value();
    }

    public String getEmail() {
        return email.value();
    }

    public LocalDate getStartDate() {
        return startDate.value();
    }

    public Integer calculateSeniorityDiscount() {
        LocalDate today = LocalDate.now();
        int years = Period.between(this.startDate.value(), today).getYears();
        if (years < 1) return 0;
        if (years <= 2) return 12;
        return 17;
    }
}
