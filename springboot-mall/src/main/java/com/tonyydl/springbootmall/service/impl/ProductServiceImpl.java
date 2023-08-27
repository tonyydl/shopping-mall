package com.tonyydl.springbootmall.service.impl;

import com.tonyydl.springbootmall.dao.ProductDao;
import com.tonyydl.springbootmall.model.Product;
import com.tonyydl.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
