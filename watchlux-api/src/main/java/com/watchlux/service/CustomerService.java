package com.watchlux.service;

import com.watchlux.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void deleteById(int id);
    List<Customer> findAll();
}
