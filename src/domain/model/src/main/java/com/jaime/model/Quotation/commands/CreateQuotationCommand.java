package com.jaime.model.Quotation.commands;


import com.jaime.model.generic.Command;

public class CreateQuotationCommand extends Command {
    private String quotationId;

    public CreateQuotationCommand() {
    }

    public CreateQuotationCommand(String quotationId) {
        this.quotationId = quotationId;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }
}
