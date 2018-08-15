package com.edvmorango.reactorpoc.service;

import com.edvmorango.reactorpoc.model.Customer;
import org.reactivestreams.Publisher;

public interface CustomerService<T extends Publisher<Customer>> {

    T create(Customer customer);

    T findById(Long id);

    T list();

}
