package com.watchlux.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String role;
}
