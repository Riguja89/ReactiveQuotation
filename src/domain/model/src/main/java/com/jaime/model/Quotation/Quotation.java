package com.jaime.model.Quotation;


import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.Quote;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.events.*;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.AggregateRoot;
import com.jaime.model.generic.DomainEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Quotation extends AggregateRoot<QuotationId> {
    protected List<Client> clients;
    protected List<Reading> readings;
    protected List<Quote> quotes;

    public Quotation(String id) {
        super(QuotationId.of(id));
        subscribe(new QuotationBehavior(this));
        appendChange(new QuotationCreated());
    }
    private Quotation(QuotationId id) {
        super(id);
        subscribe(new QuotationBehavior(this));
    }

    public void addClient(String clientId, String name, String lastName, String email, LocalDate startDate) {
        appendChange(new ClientAdded(clientId, name, lastName, email, startDate));
    }

    public void addReading(String readingId, String title, String author, Long originalPrice, String readingType) {
        appendChange(new ReadingAdded(readingId, title, author, originalPrice, readingType));
    }
    public void createQuote(String quoteId) {
        appendChange(new QuoteCreated(quoteId));
    }
    public void addReadingToQuote(String quoteId, String readingId, String title, String author, Long originalPrice, String readingType) {
        appendChange(new ReadingToQuoteAdded(quoteId,readingId,title,author,originalPrice,readingType));
    }

    public void createResumeQuote(
            String quoteResumeId,
            String nameClient,
            String startDateClient,
            String quoteType,
            Float totaDiscount,
            Float resumeTotal
    ) {
        appendChange(new QuoteResumeCreated(quoteResumeId,nameClient,startDateClient,quoteType,totaDiscount,resumeTotal));
    }

    public void addQuoteItem(
            String quoteResumeId,
            String readingTitle,
            String readingAuthor,
            String readingType,
            Float finalPrice,
            Float originalPrice,
            Float discount,
            Integer amount
    ){
        appendChange(new QuoteItemAdded(quoteResumeId,readingTitle,readingAuthor,readingType,finalPrice,originalPrice,discount,amount));
    }

    public Reading getReadingById(String readingId){
       return readings.stream().filter(reading -> reading.identity().value().equals(readingId))
               .findFirst().orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));
    }

    public Optional<Client> getClientById(String clientId){
        return clients.stream().filter(client -> client.identity().value().equals(clientId)).findFirst();
    }

    public static Quotation from(QuotationId quotationId, List<DomainEvent> events) {
        Quotation quotation = new Quotation(quotationId);
        events.forEach(quotation::applyEvent);
        return quotation;
    }

    public List<Client> getClients() {
        return clients;
    }
}
