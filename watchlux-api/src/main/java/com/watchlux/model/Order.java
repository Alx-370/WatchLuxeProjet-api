package com.watchlux.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private LocalDateTime orderDate;
    private Integer customerId;
}
