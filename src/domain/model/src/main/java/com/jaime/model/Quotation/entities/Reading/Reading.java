package com.jaime.model.Quotation.entities.Reading;

import com.jaime.model.Quotation.entities.Reading.values.*;
import com.jaime.model.generic.Entity;

public class Reading extends Entity<ReadingId> {
    private ReadingTitle title;
    private ReadingAuthor author;
    private ReadingType type;
    private ReadingOriginalPrice originalPrice;
    private ReadingAmount amount;

    public Reading(ReadingId id) {
        super(id);
    }

    private Reading(ReadingId readingId, ReadingTitle title, ReadingAuthor author, ReadingType type, ReadingOriginalPrice originalPrice,ReadingAmount amount) {
        super(readingId);
        this.title = title;
        this.author = author;
        this.type = type;
        this.originalPrice = originalPrice;
        this.amount = amount;
    }

    public static Reading from(String readingId, String title, String author, String type, Long originalPrice, Integer amount) {
        return new Reading(
                ReadingId.of(readingId),
                ReadingTitle.of(title),
                ReadingAuthor.of(author),
                ReadingType.of(type),
                ReadingOriginalPrice.of(originalPrice),
                ReadingAmount.of(amount)
        );
    }

    public String title() {
        return title.value();
    }

    public String author() {
        return author.value();
    }

    public String type() {
        return type.value();
    }

    public Float originalPrice() {
        return originalPrice.value()+0f;
    }

    public Integer amount() {
        return amount.value();
    }

    public void modifyAmount(ReadingAmount amount) {
        this.amount = amount;
    }

    public Float calculatePrice() {
        if (this.type.value().equals(ReadingTypeEnum.BOOK.toString())) {
            return this.originalPrice.value() * (1 + 33.33f / 100);
        } else {
            return this.originalPrice.value() * 2f;
        }
    }

}
