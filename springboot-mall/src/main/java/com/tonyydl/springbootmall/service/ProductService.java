package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.constant.ProductCategory;
import com.tonyydl.springbootmall.dto.ProductRequest;
import com.tonyydl.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
