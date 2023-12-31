package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("total_amount")
    private Integer totalAmount;
    @JsonProperty("order_items")
    private List<OrderItemDTO> orderItems;
    @JsonProperty("created_date")
    private Date createdDate;
    @JsonProperty("last_modified_date")
    private Date lastModifiedDate;
}
