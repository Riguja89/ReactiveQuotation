package com.jaime.addclientusecase;

import com.jaime.generic.UseCaseForCommand;
import com.jaime.generic.gateways.DomainEventRepository;
import com.jaime.model.Quotation.Quotation;
import com.jaime.model.Quotation.commands.AddClientCommand;
import com.jaime.model.Quotation.entities.Client.values.ClientId;
import com.jaime.model.Quotation.values.QuotationId;
import com.jaime.model.generic.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class AddClientUseCase extends UseCaseForCommand<AddClientCommand> {
    private final DomainEventRepository repository;

    public AddClientUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
        public Flux<DomainEvent> apply(Mono<AddClientCommand> addClientCommandMono) {
        System.out.println(addClientCommandMono + " caso de uso");
        return addClientCommandMono.flatMapMany(command -> repository.findById(command.getQuotationId())
                .collectList()
                .flatMapIterable(events -> {
                    System.out.println(command.getQuotationId());
                    Quotation quotation = Quotation.from(QuotationId.of(command.getQuotationId()), events);
                    quotation.addClient(
                            new ClientId().value(),
                            command.getName(),
                            command.getLastName(),
                            command.getEmail(),
                            LocalDate.now()
                    );

                    return quotation.getUncommitedChanges();
                }).map(event -> event).flatMap(repository::saveEvent)
        );
    }
}
