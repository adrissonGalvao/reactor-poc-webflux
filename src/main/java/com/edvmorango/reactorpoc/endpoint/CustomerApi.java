package com.edvmorango.reactorpoc.endpoint;

import com.edvmorango.reactorpoc.model.Customer;
import com.edvmorango.reactorpoc.repository.CustomerRepository;
import com.edvmorango.reactorpoc.service.CustomerService;
import com.edvmorango.reactorpoc.service.CustomerServiceImpl;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerApi {

    private CustomerService service = new CustomerServiceImpl(new CustomerRepository());


    public Mono<ServerResponse> create(ServerRequest req) {

        Mono<Customer> objectMono = req.bodyToMono(Customer.class)
                .flatMap(c ->  Mono.from(service.create(c)));


        return ServerResponse.status(201).body(objectMono, Customer.class);

    }

    public Mono<ServerResponse> list(ServerRequest req) {

        var list = service.list();

        return ServerResponse.ok().body(list, Customer.class);

    }

    public Mono<ServerResponse> findById(ServerRequest req) {

        String id = req.pathVariable("id");

        Publisher byId = service.findById(Long.valueOf(id));

        return ServerResponse.ok().body(byId, Customer.class);

    }

    public Mono<ServerResponse> update(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Update"));

    }

    public Mono<ServerResponse> delete(ServerRequest req) {

        return ServerResponse.ok().body(BodyInserters.fromObject("Delete"));

    }
}
