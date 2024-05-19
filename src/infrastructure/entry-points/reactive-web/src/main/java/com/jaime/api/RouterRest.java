package com.jaime.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(
                POST("/api/v1/CreateQuotation")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::handlerCreateQuotationUseCase
        ).andRoute(
                POST("/api/v1/AddClient")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::handlerAddClientUseCase
        ).andRoute(
                POST("/api/v1/AddReading")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::handlerAddReadingUseCase
        ).andRoute(
                POST("/api/v1/MonoQuote")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::handlerSetMonoQuote
        ).andRoute(
                POST("/api/v1/MultipleQuote")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::handlerSetMultipleQuote
        );
    }

}
