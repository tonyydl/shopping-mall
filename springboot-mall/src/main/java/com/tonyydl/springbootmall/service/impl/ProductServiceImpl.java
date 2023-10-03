package com.tonyydl.springbootmall.service.impl;

import com.tonyydl.springbootmall.data.dto.ProductQueryParamsDTO;
import com.tonyydl.springbootmall.data.dto.ProductRequestDTO;
import com.tonyydl.springbootmall.data.po.ProductPO;
import com.tonyydl.springbootmall.repository.ProductRepository;
import com.tonyydl.springbootmall.service.ProductService;
import com.tonyydl.springbootmall.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Integer countProduct(ProductQueryParamsDTO productQueryParamsDTO) {
        return productRepository.countByCategoryAndProductNameContainingIgnoreCase(
                productQueryParamsDTO.category(),
                productQueryParamsDTO.search()
        );
    }

    @Override
    public List<ProductPO> getProducts(ProductQueryParamsDTO productQueryParamsDTO) {
        Pageable pageable = PageRequest.of(productQueryParamsDTO.page(), productQueryParamsDTO.size(), createSort(productQueryParamsDTO));
        return productRepository.findProductsByCategoryAndProductNameContaining(
                productQueryParamsDTO.category(),
                productQueryParamsDTO.search(),
                pageable
        );
    }

    private Sort createSort(ProductQueryParamsDTO productQueryParamsDTO) {
        if (productQueryParamsDTO != null) {
            String orderBy = productQueryParamsDTO.orderBy();
            if (orderBy != null) {
                String orderByCamelCase = StringUtils.makeCamelCase(orderBy);
                String sort = productQueryParamsDTO.sort();
                Sort.Direction sortDirection = Sort.Direction.fromOptionalString(sort.toUpperCase())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                return Sort.by(sortDirection, orderByCamelCase);
            }
        }

        return null;
    }

    @Override
    public ProductPO getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> {
            log.warn("productId {} 不存在", productId);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Integer createProduct(ProductRequestDTO productRequestDTO) {
        Date now = new Date();
        ProductPO productPO = ProductPO
                .builder()
                .productName(productRequestDTO.getProductName())
                .category(productRequestDTO.getCategory())
                .imageUrl(productRequestDTO.getImageUrl())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .description(productRequestDTO.getDescription())
                .createdDate(now)
                .lastModifiedDate(now)
                .build();

        ProductPO savedProductPO = productRepository.save(productPO);

        return savedProductPO.getProductId();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequestDTO productRequestDTO) {
        ProductPO productPO = productRepository.findById(productId).orElseThrow(() -> {
            log.warn("productId {} 不存在", productId);
            return new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });

        productPO.setProductName(productRequestDTO.getProductName());
        productPO.setCategory(productRequestDTO.getCategory());
        productPO.setImageUrl(productRequestDTO.getImageUrl());
        productPO.setPrice(productRequestDTO.getPrice());
        productPO.setStock(productRequestDTO.getStock());
        productPO.setDescription(productRequestDTO.getDescription());
        productPO.setLastModifiedDate(new Date());

        productRepository.save(productPO);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }
}
