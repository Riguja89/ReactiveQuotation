package com.jaime.model.Quotation.commands;

import com.jaime.model.generic.Command;

import java.util.List;

public class SetMultipleQuoteCommand extends Command {
    private List<TupleReadingAmount> readingAmountList;
    private String clientId;

    public SetMultipleQuoteCommand() {
    }

    public List<TupleReadingAmount> getReadingAmountList() {
        return readingAmountList;
    }

    public void setReadingAmountList(List<TupleReadingAmount> readingAmountList) {
        this.readingAmountList = readingAmountList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
