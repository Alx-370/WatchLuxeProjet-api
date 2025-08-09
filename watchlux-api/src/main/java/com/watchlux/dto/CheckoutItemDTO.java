
package com.watchlux.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutItemDTO {
    @NotNull
    private Long productId;
    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantity;
}
