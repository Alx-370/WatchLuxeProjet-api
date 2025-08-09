
package com.watchlux.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double unitPrice;

    private String productName;
}
