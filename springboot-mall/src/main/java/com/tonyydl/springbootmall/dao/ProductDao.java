package com.tonyydl.springbootmall.dao;

import com.tonyydl.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
