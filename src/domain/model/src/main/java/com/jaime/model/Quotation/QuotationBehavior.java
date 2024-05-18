package com.jaime.model.Quotation;

import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.Quote;
import com.jaime.model.Quotation.entities.Quote.values.QuoteId;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.events.*;
import com.jaime.model.generic.EventChange;

import java.util.ArrayList;
import java.util.Optional;


public class QuotationBehavior extends EventChange {

    public QuotationBehavior(Quotation quotation) {

        apply((QuotationCreated event) -> {
            quotation.clients = new ArrayList<>();
            quotation.readings = new ArrayList<>();
            quotation.quotes = new ArrayList<>();
        });

        apply((ClientAdded event) -> {
            if (quotation.clients == null) {
                quotation.clients = new ArrayList<>();
            }
            quotation.clients.add(Client.from(
                    event.getClientId(),
                    event.getName(),
                    event.getLastName(),
                    event.getEmail(),
                    event.getStartDate()
            ));
        });

        apply((ReadingAdded event) -> {
            if (quotation.readings == null) {
                quotation.readings = new ArrayList<>();
            }
            quotation.readings.add(Reading.from(
                    event.getReadingId(),
                    event.getTitle(),
                    event.getAuthor(),
                    event.getType(),
                    event.getOriginalPrice(),
                    1
            ));
        });

        apply((QuotationCreated event) -> {
            if (quotation.quotes == null) {
                quotation.quotes = new ArrayList<>();
            }
        });

        apply((ReadingToQuoteAdded event) -> {
            if (quotation.quotes == null) {
                quotation.quotes = new ArrayList<>();
            }
            Optional<Quote> quoteOptional = quotation.quotes.stream().filter(quote -> quote.identity().value().equals(event.getQuoteId())).findFirst();
            if (quoteOptional.isPresent()) {
                quoteOptional.get().readings().add(
                        Reading.from(
                                event.getReadingId(),
                                event.getTitle(),
                                event.getAuthor(),
                                event.getReadingType(),
                                event.getOriginalPrice(),
                                1
                        )
                );
            } else {
                throw new IllegalArgumentException("no existe Quote");
            }

        });

        apply((QuoteItemAdded event) -> {


        });
    }
}
