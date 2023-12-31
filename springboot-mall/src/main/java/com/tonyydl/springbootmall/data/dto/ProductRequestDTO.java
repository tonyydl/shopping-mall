package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tonyydl.springbootmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull
    @JsonProperty("product_name")
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    @JsonProperty("image_url")
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;
}
