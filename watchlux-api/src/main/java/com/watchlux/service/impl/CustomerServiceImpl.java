package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.model.Customer;
import com.watchlux.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void deleteById(int id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
