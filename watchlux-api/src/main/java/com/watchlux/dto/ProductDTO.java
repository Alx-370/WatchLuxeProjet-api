
package com.watchlux.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    private String description;
    @PositiveOrZero(message = "Le prix doit être positif ou nul")
    private double price;
    @Min(value = 0, message = "Le stock doit être >= 0")
    private int stock;
}
