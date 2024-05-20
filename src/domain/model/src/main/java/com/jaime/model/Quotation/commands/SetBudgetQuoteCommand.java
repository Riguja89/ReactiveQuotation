package com.jaime.model.Quotation.commands;

import com.jaime.model.generic.Command;

import java.util.List;

public class SetBudgetQuoteCommand extends Command {
    private List<String> readingsIdsList;
    private String clientId;
    private Float budget;

    public SetBudgetQuoteCommand() {
    }

    public List<String> getReadingsIdsList() {
        return readingsIdsList;
    }

    public void setReadingsIdsList(List<String> readingsIdsList) {
        this.readingsIdsList = readingsIdsList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }
}
