package com.tonyydl.springbootmall.data.dto;

import com.tonyydl.springbootmall.constant.ProductCategory;
import org.springframework.data.domain.Pageable;

public record ProductQueryParamsDTO(
        ProductCategory category,
        String search,
        Pageable pageable) {
}
