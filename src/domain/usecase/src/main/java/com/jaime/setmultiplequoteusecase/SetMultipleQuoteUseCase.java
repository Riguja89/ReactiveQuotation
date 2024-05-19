package com.jaime.setmultiplequoteusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.UseCasesEnum;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.SetMultipleQuoteCommand;
import com.jaime.model.Quotation.commands.TupleReadingAmount;
import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.values.QuoteId;
import com.jaime.model.Quotation.entities.Quote.values.QuoteTypeEnum;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.entities.Reading.values.ReadingAmount;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class SetMultipleQuoteUseCase extends UseCaseForCommand<SetMultipleQuoteCommand> {
    private final DomainEventRepository repository;

    public SetMultipleQuoteUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SetMultipleQuoteCommand> setMultipleQuoteCommandMono) {


        return setMultipleQuoteCommandMono.flatMapMany(command -> repository.findClientsAndReadings()
                .collectList()
                .flatMapIterable(events -> {
                    Quotation quotation = Quotation.from(QuotationId.of(UseCasesEnum.miclavepersonal77.toString()), events);

                    Client client = quotation.getClientById(command.getClientId())
                            .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));

                    Integer seniorityDiscount = client.calculateSeniorityDiscount();
                    String quoteResumeId = new QuoteId().value();
                    final Float[] resumeTotal = {0f};
                    final Float[] resumeDiscount = {0f};
                    final Integer[] readingsAmount = {0};
                    final String[] typeQuote = {QuoteTypeEnum.DETAIL.toString()};

                    Flux<TupleReadingAmount> myFlux = Flux.fromIterable(command.getReadingAmountList());

                    Flux<Reading> readings = myFlux.map(tuple -> {
                        Reading copy = quotation.getReadingById(tuple.getReadingId());
                        copy.modifyAmount(ReadingAmount.of(tuple.getAmount()));
                        readingsAmount[0] += tuple.getAmount();
                        return copy;
                    });

                    readings.map(reading -> {
                        Float mayorDiscount = 0.0f;

                        if (readingsAmount[0] > 10) {
                            mayorDiscount = 0.15f * (readingsAmount[0] - 10);
                            typeQuote[0] = QuoteTypeEnum.MAYOR.toString();
                        }

                        Float finalPrice = reading.calculatePrice();
                        Float discount = -finalPrice * 0.02f;
                        readingsAmount[0] = readingsAmount[0] + reading.amount();
                        finalPrice = finalPrice - discount;
                        discount = (seniorityDiscount / 100f) * finalPrice + discount;
                        finalPrice = finalPrice - discount;
                        finalPrice = finalPrice * reading.amount();
                        discount = discount * reading.amount();
                        Float discountMayor = finalPrice * (mayorDiscount / 100f);
                        discount += discountMayor;
                        finalPrice -= discount;
                        resumeTotal[0] = resumeTotal[0] + finalPrice;
                        resumeDiscount[0] = resumeDiscount[0] + discount;

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
                        return reading;
                    }).subscribe();

                    quotation.createResumeQuote(
                            quoteResumeId,
                            (client.getName() + " " + client.getLastName()),
                            client.getStartDate().toString(),
                            typeQuote[0],
                            resumeDiscount[0],
                            resumeTotal[0]
                    );

                    return quotation.getUncommitedChanges().sort(Comparator.);
                }).flatMap(repository::saveEvent)
        );


    }
}
