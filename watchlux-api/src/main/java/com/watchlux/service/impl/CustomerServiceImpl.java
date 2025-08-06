package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.model.Customer;
import com.watchlux.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public void register(Customer customer) {
        customerDao.save(customer);
    }
}
