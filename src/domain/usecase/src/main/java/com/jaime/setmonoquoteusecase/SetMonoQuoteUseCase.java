package com.jaime.setmonoquoteusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.UseCasesEnum;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.AddClientCommand;
import com.jaime.model.Quotation.commands.SetMonoQuoteCommand;
import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Client.values.ClientId;
import com.jaime.model.Quotation.entities.Quote.values.QuoteId;
import com.jaime.model.Quotation.entities.Quote.values.QuoteTypeEnum;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SetMonoQuoteUseCase extends UseCaseForCommand<SetMonoQuoteCommand> {
    private final DomainEventRepository repository;

    public SetMonoQuoteUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SetMonoQuoteCommand> setMonoQuoteCommandMono) {

        System.out.println(setMonoQuoteCommandMono + " caso de uso");
        return setMonoQuoteCommandMono.flatMapMany(command -> repository.findClientsAndReadings()
                .collectList()
                .flatMapIterable(events -> {
                    Quotation quotation = Quotation.from(QuotationId.of(UseCasesEnum.miclavepersonal77.toString()), events);
                    Reading reading = quotation.getReadingById(command.getReadingId());
                    Client client = quotation.getClientById(command.getClientId())
                            .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));

                    Integer seniorityDiscount = client.calculateSeniorityDiscount();
                    Float finalPrice = reading.calculatePrice();
                    Float discount = -finalPrice * 0.02f;
                    finalPrice = finalPrice - discount;
                    discount = (seniorityDiscount / 100f) * finalPrice + discount;
                    finalPrice = finalPrice - discount;
                    Float total = finalPrice;

                    String quoteResumeId = new QuoteId().value();

                    quotation.addQuoteItem(
                            quoteResumeId,
                            reading.title(),
                            reading.author(),
                            reading.type(),
                            finalPrice,
                            reading.originalPrice(),
                            discount,
                            reading.amount()
                    );
                    quotation.createResumeQuote(
                            quoteResumeId,
                            (client.getName() + " " + client.getLastName()),
                            client.getStartDate().toString(),
                            QuoteTypeEnum.DETAIL.toString(),
                            discount,
                            total

                    );

                    return quotation.getUncommitedChanges();
                }).map(event -> event).flatMap(repository::saveEvent)
        );
    }
}
