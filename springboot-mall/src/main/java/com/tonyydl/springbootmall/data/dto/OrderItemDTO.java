package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @JsonProperty("order_item_id")
    private Integer orderItemId;
    private Integer quantity;
    private Integer amount;

    private ProductDTO product;
}
