package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class ReadingToQuoteAdded extends DomainEvent {
    private String quoteId;
    private String readingId;
    private String title;
    private String author;
    private String readingType;
    private Long originalPrice;

    public ReadingToQuoteAdded() {
        super(EventsEnum.READING_TO_QUOTE_ADDED.toString());
    }

    public ReadingToQuoteAdded(String quoteId, String readingId, String title, String author, Long originalPrice, String readingType) {
        super(EventsEnum.READING_TO_QUOTE_ADDED.toString());
        this.quoteId=quoteId;
        this.readingId = readingId;
        this.title = title;
        this.author = author;
        this.originalPrice = originalPrice;
        this.readingType = readingType;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getReadingType() {
        return readingType;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }
}
