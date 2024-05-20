package com.jaime.api;

import com.jaime.addclientusecase.AddClientUseCase;
import com.jaime.addreadinusecase.AddReadingUseCase;
import com.jaime.createquotationusecase.CreateQuotationUseCase;
import com.jaime.model.Quotation.commands.*;
import com.jaime.model.generic.DomainEvent;
import com.jaime.setbudgetquoteusecase.SetBudgetQuoteUseCase;
import com.jaime.setmonoquoteusecase.SetMonoQuoteUseCase;
import com.jaime.setmultiplequoteusecase.SetMultipleQuoteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CreateQuotationUseCase createQuotationUseCase;
    private final AddClientUseCase addClientUseCase;
    private final AddReadingUseCase addReadingUseCase;
    private final SetMonoQuoteUseCase setMonoQuoteUseCase;
    private final SetMultipleQuoteUseCase setMultipleQuoteUseCase;
    private final SetBudgetQuoteUseCase setBudgetQuoteUseCase;


    public Mono<ServerResponse> handlerCreateQuotationUseCase(ServerRequest serverRequest) {

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(createQuotationUseCase
                        .apply(serverRequest.bodyToMono(CreateQuotationCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + "si hay error"))), DomainEvent.class))
                .onErrorResume(e -> Mono.just("Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(s)));
    }

    public Mono<ServerResponse> handlerAddClientUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(addClientUseCase
                        .apply(serverRequest.bodyToMono(AddClientCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + " si hay error"))), DomainEvent.class));
    }

    public Mono<ServerResponse> handlerAddReadingUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(addReadingUseCase
                        .apply(serverRequest.bodyToMono(AddReadingCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + " si hay error"))), DomainEvent.class));
    }

    public Mono<ServerResponse> handlerSetMonoQuote(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(setMonoQuoteUseCase
                        .apply(serverRequest.bodyToMono(SetMonoQuoteCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + " si hay error"))), DomainEvent.class));
    }

    public Mono<ServerResponse> handlerSetMultipleQuote(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(setMultipleQuoteUseCase
                        .apply(serverRequest.bodyToMono(SetMultipleQuoteCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + " si hay error"))), DomainEvent.class));
    }

    public Mono<ServerResponse> handlerSetBudgetQuote(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(setBudgetQuoteUseCase
                        .apply(serverRequest.bodyToMono(SetBudgetQuoteCommand.class)
                                .doOnError((e) -> System.out.println(e.getMessage() + " si hay error"))), DomainEvent.class));
    }

    public Mono<ServerResponse> helloWorld(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromValue("Hola Mundo"));
    }


}
