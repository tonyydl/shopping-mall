package com.tonyydl.springbootmall.data.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyItemDTO {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer quantity;
}
