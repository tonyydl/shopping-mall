package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.data.dto.ProductQueryParamsDTO;
import com.tonyydl.springbootmall.data.dto.ProductRequestDTO;
import com.tonyydl.springbootmall.data.po.ProductPO;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParamsDTO productQueryParamsDTO);

    List<ProductPO> getProducts(ProductQueryParamsDTO productQueryParamsDTO);

    ProductPO getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);

    void deleteProductById(Integer productId);
}
