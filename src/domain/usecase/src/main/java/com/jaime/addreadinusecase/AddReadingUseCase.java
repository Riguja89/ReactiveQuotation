package com.jaime.addreadinusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.AddReadingCommand;
import com.jaime.model.Quotation.entities.Reading.values.ReadingId;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AddReadingUseCase extends UseCaseForCommand<AddReadingCommand> {
    private final DomainEventRepository repository;

    public AddReadingUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddReadingCommand> addReadingCommandMono) {
        System.out.println(addReadingCommandMono + " caso de uso");
        return addReadingCommandMono.flatMapMany(command -> repository.findById(command.getQuotationId())
                .collectList()
                .flatMapIterable(events -> {
                    System.out.println(command.getQuotationId());
                    Quotation quotation = Quotation.from(QuotationId.of(command.getQuotationId()), events);
                    quotation.addReading(
                            new ReadingId().value(),
                            command.getTitle(),
                            command.getAuthor(),
                            command.getOriginalPrice(),
                            command.getType()
                    );
                    return quotation.getUncommitedChanges();
                }).map(event -> event).flatMap(repository::saveEvent)
        );
    }
}
