package com.jaime.model.Quotation.commands;

import com.jaime.model.generic.Command;

public class SetMonoQuoteCommand extends Command {
    private String clientId;
    private String readingId;

    public SetMonoQuoteCommand() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }
}
