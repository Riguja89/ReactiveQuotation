package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class QuoteItemAdded extends DomainEvent {
    private String quoteResumeId;
   // private String quoteItemId;
    private String readingTitle;
    private String readingAuthor;
    private String readingType;
    private Float finalPrice;
    private Float originalPrice;
    private Float discount;
    private Integer amount;

    public QuoteItemAdded() {
        super(EventsEnum.QUOTE_ITEM_ADDED.toString());
    }

    public QuoteItemAdded(
            String quoteResumeId,
            String readingTitle,
            String readingAuthor,
            String readingType,
            Float finalPrice,
            Float originalPrice,
            Float discount,
            Integer amount
    ) {
        super(EventsEnum.QUOTE_ITEM_ADDED.toString());
        this.quoteResumeId = quoteResumeId;
        this.readingTitle = readingTitle;
        this.readingAuthor = readingAuthor;
        this.readingType = readingType;
        this.finalPrice = finalPrice;
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.amount = amount;
    }

    public String getQuoteResumeId() {
        return quoteResumeId;
    }

    public void setQuoteResumeId(String quoteResumeId) {
        this.quoteResumeId = quoteResumeId;
    }

    public String getReadingTitle() {
        return readingTitle;
    }

    public void setReadingTitle(String readingTitle) {
        this.readingTitle = readingTitle;
    }

    public String getReadingAuthor() {
        return readingAuthor;
    }

    public void setReadingAuthor(String readingAuthor) {
        this.readingAuthor = readingAuthor;
    }

    public String getReadingType() {
        return readingType;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
