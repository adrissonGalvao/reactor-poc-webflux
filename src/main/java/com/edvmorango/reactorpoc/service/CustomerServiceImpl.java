package com.edvmorango.reactorpoc.service;

import com.edvmorango.reactorpoc.model.Customer;
import com.edvmorango.reactorpoc.repository.CustomerRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository rep;

    public CustomerServiceImpl(CustomerRepository rep) {
        this.rep = rep;
    }

    public Mono<Customer> createFlated(Mono<Customer> customer) {

      return rep.create(customer)
              .flatMap(rep::findById);

    }

    public Mono<Customer> create(Customer customer) {
        return null;
    }

    public Mono<Customer> findById(Long id) {
        return rep.findById(id);
    }

    public Flux<Customer> list() {
        return rep.list();
    }

}
