package com.jaime.model.Quotation.events;

import com.jaime.model.generic.DomainEvent;

public class ReadingAdded extends DomainEvent {
    private String readingId;
    private String title;
    private String author;
    private String type;
    private Long originalPrice;

    public ReadingAdded() {
        super(EventsEnum.READING_ADDED.toString());
    }

    public ReadingAdded(String readingId, String title, String author, Long originalPrice, String readingType) {
        super(EventsEnum.READING_ADDED.toString());
        this.readingId = readingId;
        this.title = title;
        this.author = author;
        this.originalPrice = originalPrice;
        this.type = readingType;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }
}
