
package com.watchlux.dao;

import com.watchlux.entity.Customer;
import java.util.Optional;

public interface CustomerDao {
    Optional<Customer> findByEmail(String email);
    Customer create(Customer c);
}
