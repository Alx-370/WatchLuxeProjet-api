
package com.watchlux.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private double total;
    private Instant createdAt;
    private List<OrderItemDTO> items;
}
