package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tonyydl.springbootmall.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "product_id",
        "product_name",
        "category",
        "image_url",
        "price",
        "stock",
        "description",
        "created_date",
        "last_modified_date"
})
public class ProductDTO {

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String productName;

    private ProductCategory category;

    @JsonProperty("image_url")
    private String imageUrl;

    private Integer price;

    private Integer stock;

    private String description;

    @JsonProperty("created_date")
    private long createdDate;

    @JsonProperty("last_modified_date")
    private long lastModifiedDate;
}
