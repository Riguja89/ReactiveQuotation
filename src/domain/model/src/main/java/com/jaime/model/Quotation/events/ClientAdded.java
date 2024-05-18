package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

import java.time.LocalDate;

public class ClientAdded extends DomainEvent {
    private String clientId;
    private String name;
    private String lastName;
    private String email;
    private LocalDate startDate;

    public ClientAdded(String clientId, String name, String lastName, String email, LocalDate startDate) {
        super(EventsEnum.CLIENT_ADDED.toString());
        this.clientId = clientId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
    }

    public ClientAdded() {
        super(EventsEnum.CLIENT_ADDED.toString());
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
