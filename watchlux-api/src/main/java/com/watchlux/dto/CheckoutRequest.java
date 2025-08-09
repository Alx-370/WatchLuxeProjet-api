
package com.watchlux.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutRequest {
    @Email(message = "Email invalide")
    @NotBlank(message = "Email obligatoire")
    private String email;
    @NotEmpty(message = "La liste des articles ne peut être vide")
    @Valid
    private List<CheckoutItemDTO> items;
    private String name;
}
