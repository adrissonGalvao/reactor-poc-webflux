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


    Mono<ServerResponse> create(ServerRequest req) {

        Mono<Customer> objectMono = req.bodyToMono(Customer.class)
                .flatMap(c ->  Mono.from(service.create(c)));


        return ServerResponse.status(201).body(objectMono, Customer.class);

    }

    Mono<ServerResponse> list(ServerRequest req) {

        var list = service.list();

        return ServerResponse.ok().body(list, Customer.class);

    }

    Mono<ServerResponse> findById(ServerRequest req) {

        String id = req.pathVariable("id");

        Mono<Customer> byId = Mono.from(service.findById(Long.valueOf(id)));

        return ServerResponse.ok().body(byId, Customer.class).switchIfEmpty(ServerResponse.notFound().build());

    }

    Mono<ServerResponse> update(ServerRequest req) {

        Mono<Customer> upd = req.bodyToMono(Customer.class)
                .flatMap(c -> Mono.from(service.update(c)));

        return ServerResponse.ok().body(upd, Customer.class).switchIfEmpty(ServerResponse.notFound().build());

    }

    Mono<ServerResponse> delete(ServerRequest req) {

        Long id = Long.valueOf(req.pathVariable("id"));

        Mono<Void> res = Mono.from(service.delete(id));

        return ServerResponse.status(204).body(res, Void.class).switchIfEmpty(ServerResponse.notFound().build());

    }
}
