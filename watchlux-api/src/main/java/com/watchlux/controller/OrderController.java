
package com.watchlux.controller;

import com.watchlux.dto.CheckoutRequest;
import com.watchlux.dto.OrderDTO;
import com.watchlux.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public OrderDTO checkout(@Valid @RequestBody CheckoutRequest req) {
        return orderService.checkout(req);
    }

    @GetMapping("/orders")
    public List<OrderDTO> listByEmail(@RequestParam String email) {
        return orderService.listByEmail(email);
    }

    @GetMapping("/admin/orders")
    public List<OrderDTO> listAll() {
        return orderService.listAll();
    }
}
