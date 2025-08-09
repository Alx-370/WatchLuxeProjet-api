
package com.watchlux.entity;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long customerId;
    private double total;
    private Instant createdAt;
    private List<OrderItem> items;
}
