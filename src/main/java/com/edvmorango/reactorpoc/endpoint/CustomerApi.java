package com.edvmorango.reactorpoc.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerApi {

    public Mono<ServerResponse> create(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Create"));

    }

    public Mono<ServerResponse> list(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("List"));

    }

    public Mono<ServerResponse> findById(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Find by id"));

    }

    public Mono<ServerResponse> update(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Update"));

    }

    public Mono<ServerResponse> delete(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Delete"));

    }
}
