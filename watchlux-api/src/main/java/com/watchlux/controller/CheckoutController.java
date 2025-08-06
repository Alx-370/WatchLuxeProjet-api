package com.watchlux.controller;

import com.watchlux.dto.CheckoutRequest;
import com.watchlux.model.OrderItem;
import com.watchlux.service.CheckoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<String> checkout(@Valid @RequestBody CheckoutRequest request) {
        List<OrderItem> items = request.getItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setProductId(dto.getProductId());
            item.setQuantity(dto.getQuantity());
            return item;
        }).collect(Collectors.toList());

        checkoutService.checkout(request.getEmail(), items);

        return ResponseEntity.ok("Commande enregistrée avec succès !");
    }
}
