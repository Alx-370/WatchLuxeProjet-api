package com.watchlux.service;

import com.watchlux.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(int id);
    void register(Customer customer);
}
