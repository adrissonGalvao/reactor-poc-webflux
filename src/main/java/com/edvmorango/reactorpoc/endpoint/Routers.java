package com.edvmorango.reactorpoc.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Routers {


    @Bean
    public RouterFunction<ServerResponse> route(CustomerApi api) {
        return RouterFunctions
                .route(RequestPredicates.POST("/customer"), api::create)
                .andRoute(RequestPredicates.GET("/customer"), api::list)
                .andRoute(RequestPredicates.GET("/customer/{id}"), api::findById)
                .andRoute(RequestPredicates.PUT("/customer/{id}"), api::update)
                .andRoute(RequestPredicates.DELETE("/customer/{id}"), api::delete);

    }


}
