package com.edvmorango.reactorpoc.service;

import com.edvmorango.reactorpoc.model.Customer;
import org.reactivestreams.Publisher;

public interface CustomerService {

    Publisher<Customer> create(Customer customer);

    Publisher<Customer> findById(Long id);

    Publisher<Customer> list();

    Publisher<Customer> update(Customer customer);

    Publisher<Void> delete(Long id);

}
