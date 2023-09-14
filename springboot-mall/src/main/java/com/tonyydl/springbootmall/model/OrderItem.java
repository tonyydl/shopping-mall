package com.tonyydl.springbootmall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer amount;
    private String productName;
    private String imageUrl;
}
