package com.tonyydl.springbootmall.data.dto;

import com.tonyydl.springbootmall.constant.ProductCategory;

public record ProductQueryParamsDTO(ProductCategory category, String search, String orderBy, String sort, Integer limit,
                                    Integer offset) {
}
