package com.jaime.model.Quotation.commands;

public class TupleReadingAmount {
    private String readingId;
    private Integer amount;

    public TupleReadingAmount() {
    }

    public TupleReadingAmount(String readingId, Integer amount) {
        this.readingId = readingId;
        this.amount = amount;
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
