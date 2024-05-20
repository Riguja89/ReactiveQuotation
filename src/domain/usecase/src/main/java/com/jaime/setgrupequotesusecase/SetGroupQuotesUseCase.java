package com.jaime.setgrupequotesusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.UseCasesEnum;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.helpers.HelpersUseCase;
import com.jaime.helpers.ResponseItem;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.SetGroupQuotesCommand;
import com.jaime.model.Quotation.entities.Client.Client;
import com.jaime.model.Quotation.entities.Quote.values.QuoteId;
import com.jaime.model.Quotation.entities.Quote.values.QuoteTypeEnum;
import com.jaime.model.Quotation.entities.Reading.Reading;
import com.jaime.model.Quotation.entities.Reading.values.ReadingAmount;
import com.jaime.model.Quotation.entities.Reading.values.ReadingTypeEnum;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.Command;
import com.jaime.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SetGroupQuotesUseCase extends UseCaseForCommand<SetGroupQuotesCommand> {
    private final DomainEventRepository repository;

    public SetGroupQuotesUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SetGroupQuotesCommand> setGroupQuotesCommandMono) {


        return setGroupQuotesCommandMono.flatMapMany(command -> repository.findClientsAndReadings()
                .collectList()
                .flatMapIterable(events -> {

                    Quotation quotation = Quotation.from(QuotationId.of(UseCasesEnum.miclavepersonal77.toString()), events);
                    Client client = quotation.getClientById(command.getClientId());
                    Integer seniorityDiscount = client.calculateSeniorityDiscount();
                    String groupQuotesId = new QuoteId().value();
                    final Float[] groupTotal = {0f};
                    final Float[] groupDiscount = {0f};


                    Flux.fromIterable(command.getGroupTuplesReadingLists()).doOnNext(group -> {

                        final Integer[] totalAmountReadingsGroup = {0};
                        String quoteResumeId = new QuoteId().value();
                        final Float[] resumeTotal = {0f};
                        final Float[] resumeDiscount = {0f};
                        final String[] typeQuote = {QuoteTypeEnum.DETAIL.toString()};

                        Flux.fromIterable(group.getReadingAmountList())
                                .map(tuple -> {
                                    totalAmountReadingsGroup[0] += tuple.getAmount();
                                    Reading reading = quotation.getReadingById(tuple.getReadingId());
                                    reading.modifyAmount(ReadingAmount.of(tuple.getAmount()));
                                    return reading;
                                })
                                .sort(Comparator.comparingDouble(Reading::calculatePrice).reversed())
                                .doOnNext(reading -> {
                                    ResponseItem responseItem = HelpersUseCase.calculateItem(reading, totalAmountReadingsGroup[0], seniorityDiscount);
                                    if (totalAmountReadingsGroup[0] > 10) typeQuote[0] = QuoteTypeEnum.MAYOR.toString();
                                    quotation.addQuoteItem(
                                            quoteResumeId,
                                            reading.title(),
                                            reading.author(),
                                            reading.type(),
                                            responseItem.getItemTotal(),
                                            reading.originalPrice(),
                                            responseItem.getItemDiscount(),
                                            reading.amount()
                                    );
                                    resumeTotal[0] += responseItem.getItemTotal();
                                    resumeDiscount[0] += responseItem.getItemDiscount();
                                }).subscribe();

                        quotation.createResumeQuote(
                                quoteResumeId,
                                groupQuotesId,
                                (client.getName() + " " + client.getLastName()),
                                client.getStartDate().toString(),
                                typeQuote[0],
                                resumeDiscount[0],
                                resumeTotal[0]
                        );
                        groupTotal[0] += resumeTotal[0];
                        groupDiscount[0] += resumeDiscount[0];

                    }).doFinally(d -> {
                        quotation.createGroupQuotes(
                                (client.getName() + " " + client.getLastName()),
                                groupQuotesId,
                                groupDiscount[0],
                                groupTotal[0]
                        );
                    }).subscribe();


                    return quotation.getUncommitedChanges();
                }).map(event -> event)//.flatMap(repository::saveEvent)
        );


    }
}