package com.tonyydl.springbootmall.dto;

import com.tonyydl.springbootmall.constant.ProductCategory;

public record ProductQueryParams(ProductCategory category, String search, String orderBy, String sort) {
}
