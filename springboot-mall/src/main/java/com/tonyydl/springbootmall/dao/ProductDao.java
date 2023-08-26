package com.tonyydl.springbootmall.dao;

import com.tonyydl.springbootmall.dto.ProductRequest;
import com.tonyydl.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
