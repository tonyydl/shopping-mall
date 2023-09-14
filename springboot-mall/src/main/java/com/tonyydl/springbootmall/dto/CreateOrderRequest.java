package com.tonyydl.springbootmall.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotEmpty
    private List<BuyItem> buyItemList;
}
