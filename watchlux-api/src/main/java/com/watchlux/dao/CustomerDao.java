package com.watchlux.dao;

import com.watchlux.model.Customer;

import java.util.Optional;

public interface CustomerDao {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(int id);
    void save(Customer customer);
}
