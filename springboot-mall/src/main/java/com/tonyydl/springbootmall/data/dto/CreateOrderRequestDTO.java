package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CreateOrderRequestDTO {

    @NotEmpty
    @JsonProperty("buyItemList")
    private List<BuyItemDTO> buyItemDTOList;
}
