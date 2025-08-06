package com.watchlux.service.impl;

import com.watchlux.dao.CustomerDao;
import com.watchlux.model.Customer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerDao customerDao;

    public CustomUserDetailsService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec email: " + email));

        return new User(
                customer.getEmail(),
                customer.getPassword(),
                List.of(new SimpleGrantedAuthority(customer.getRole()))
        );
    }
}
