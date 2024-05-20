package com.jaime.setbudgetquoteusecase;

import ch.qos.logback.core.model.INamedModel;
import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.UseCasesEnum;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.SetBudgetQuoteCommand;
import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.values.QuoteId;
import com.jaime.model.Quotation.entities.Quote.values.QuoteTypeEnum;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.entities.Reading.values.ReadingAmount;
import com.jaime.model.Quotation.entities.Reading.values.ReadingTypeEnum;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class SetBudgetQuoteUseCase extends UseCaseForCommand<SetBudgetQuoteCommand> {
    private final DomainEventRepository repository;

    public SetBudgetQuoteUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SetBudgetQuoteCommand> setBudgetQuoteCommandMono) {


        return setBudgetQuoteCommandMono.flatMapMany(command -> repository.findClientsAndReadings()
                .collectList()
                .flatMapIterable(events -> {

                    Quotation quotation = Quotation.from(QuotationId.of(UseCasesEnum.miclavepersonal77.toString()), events);
                    Client client = quotation.getClientById(command.getClientId());
                    Integer seniorityDiscount = client.calculateSeniorityDiscount();
                    String quoteResumeId = new QuoteId().value();
                    final Float[] resumeTotal = {0f};
                    final Float[] resumeDiscount = {0f};
                    final Integer[] readingsAmount = {0};
                    final String[] typeQuote = {QuoteTypeEnum.MAYOR.toString()};

                    Flux<Reading> readings = Flux.fromIterable(command.getReadingsIdsList())
                            .map(quotation::getReadingById)
                            .sort(Comparator.comparingDouble(Reading::calculatePrice));

                    Reading book = readings.filter(reading -> reading.type()
                                    .equals(ReadingTypeEnum.BOOK.toString()))
                            .switchIfEmpty(Mono.error(new RuntimeException("Debe por lo menos haber un elemento de tipo Libro")))
                            .elementAt(0)
                            .block();

                    Reading novel = readings.filter(reading -> reading.type()
                                    .equals(ReadingTypeEnum.NOVEL.toString()))
                            .switchIfEmpty(Mono.error(new RuntimeException("Debe por lo menos haber un elemento de tipo Novela")))
                            .elementAt(0)
                            .block();

                    Reading firstItem;
                    Reading secondItem;

                    if (novel.calculatePrice() > book.calculatePrice()) {
                        firstItem = novel;
                        secondItem = book;
                    } else {
                        secondItem = novel;
                        firstItem = book;
                    }

                     List<Float> values = calculateItem(firstItem, readingsAmount[0]+1, seniorityDiscount);
                    Float totalFirst = values.get(0);
                    resumeDiscount[0]=values.get(1);
                    quotation.addQuoteItem(
                            quoteResumeId,
                            firstItem.title(),
                            firstItem.author(),
                            firstItem.type(),
                            values.get(0),
                            firstItem.originalPrice(),
                            values.get(1),
                            firstItem.amount()
                    );

                    secondItem.modifyAmount(ReadingAmount.of(0));
                    final List<Float>[] values2 = new List[]{new ArrayList<>(List.of(0f, 0f))};
                    Flux.just(resumeTotal[0]).expand(total -> {
                        if (total < command.getBudget()-secondItem.calculatePrice()) {
                            secondItem.modifyAmount(ReadingAmount.of(secondItem.amount()+1));
                            values2[0] = calculateItem(secondItem, readingsAmount[0]++, seniorityDiscount);
                            resumeTotal[0]= values2[0].get(0)+totalFirst;
                            return Flux.just(resumeTotal[0]);
                        } else {
                            if(secondItem.amount()<9){
                                return Mono.error(new RuntimeException("Presupuesto insuficiente"));
                            }
                            quotation.addQuoteItem(
                                    quoteResumeId,
                                    secondItem.title(),
                                    secondItem.author(),
                                    secondItem.type(),
                                    values2[0].get(0),
                                    secondItem.originalPrice(),
                                    values2[0].get(1),
                                    secondItem.amount()
                            );
                            resumeDiscount[0]+=values2[0].get(1);
                            return Flux.empty();
                        }
                    }).doOnError(e->{
                        quotation.markChangeAsCommitted();
                        System.out.println("Error: Falta presupuesto");
                    }).then().doOnSuccess(
                            s -> {
                                quotation.createResumeQuote(
                                        quoteResumeId,
                                        (client.getName() + " " + client.getLastName()),
                                        client.getStartDate().toString(),
                                        typeQuote[0],
                                        resumeDiscount[0],
                                        resumeTotal[0]
                                );
                            }
                    ).subscribe();

                    return quotation.getUncommitedChanges();
                }).map(event -> event)//.flatMap(repository::saveEvent)
        );


    }

    private static List<Float> calculateItem(Reading reading, Integer readingsAmount, Integer seniority) {

        Float mayorDiscount = 0.0f;
        Float finalPrice = reading.calculatePrice();
        Float discount = 0f;
        Float discountFinal = 0f;
        Float discountMayor;

        if (readingsAmount > 10) {
            mayorDiscount = 0.15f * (readingsAmount);
            discount = -finalPrice * 0.02f;
        }

        finalPrice = finalPrice - discount;
        discountFinal+=discount;
        discount = (seniority / 100f) * finalPrice;
        finalPrice = finalPrice - discount;
        discountFinal+=discount;
        finalPrice = finalPrice * reading.amount();
        discount = discount * reading.amount();
        discountFinal+=discount;
        discountMayor = finalPrice * (mayorDiscount / 100f);
        discount += discountMayor;
        discountFinal+=discount;
        finalPrice -= discount;

        return new ArrayList<>(List.of(finalPrice, discountFinal));

    }
}
