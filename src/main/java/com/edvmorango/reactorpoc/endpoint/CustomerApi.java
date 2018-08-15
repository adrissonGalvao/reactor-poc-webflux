package com.edvmorango.reactorpoc.endpoint;

import com.edvmorango.reactorpoc.model.Customer;
import com.edvmorango.reactorpoc.repository.CustomerRepository;
import com.edvmorango.reactorpoc.service.CustomerService;
import com.edvmorango.reactorpoc.service.CustomerServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerApi {

    private CustomerService service = new CustomerServiceImpl(new CustomerRepository());


    public Mono<ServerResponse> create(ServerRequest req) {

        Mono<Customer> objectMono = req.bodyToMono(Customer.class)
                .flatMap(c ->  Mono.from(service.create(c)));

        return ServerResponse.ok().body(objectMono, Customer.class);

//        return ServerResponse.ok().body(BodyInserters.fromObject("Create"));

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
