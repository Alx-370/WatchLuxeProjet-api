package com.watchlux.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutRequest {

    @Email(message = "Email invalide")
    @NotEmpty(message = "L'email est obligatoire")
    private String email;

    @NotEmpty(message = "La liste des articles ne peut être vide")
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        @NotNull(message = "L'identifiant du produit est obligatoire")
        private Integer productId;

        @NotNull(message = "La quantité est obligatoire")
        @Min(value = 1, message = "La quantité doit être au moins 1")
        private Integer quantity;
    }
}
