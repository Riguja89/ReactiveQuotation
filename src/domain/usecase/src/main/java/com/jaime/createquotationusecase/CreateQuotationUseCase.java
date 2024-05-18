package com.jaime.createquotationusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.CreateQuotationCommand;
import com.jaime.model.generic.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreateQuotationUseCase extends UseCaseForCommand<CreateQuotationCommand> {
    private final DomainEventRepository repository;

    public CreateQuotationUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateQuotationCommand> createQuotationCommandMono) {
        System.out.println(createQuotationCommandMono +" caso de uso");
        return createQuotationCommandMono.flatMapIterable(command -> {
            System.out.println("quotationId: " + command.getQuotationId());
            Quotation quotation = new Quotation(
                    command.getQuotationId()
            );
            System.out.println("si va a guardar" + quotation.getUncommitedChanges());
            return quotation.getUncommitedChanges();
        }).map(event -> event).flatMap(repository::saveEvent);
    }
}
