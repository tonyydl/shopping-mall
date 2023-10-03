package com.tonyydl.springbootmall.controller;

import com.tonyydl.springbootmall.constant.ProductCategory;
import com.tonyydl.springbootmall.data.dto.ProductQueryParamsDTO;
import com.tonyydl.springbootmall.data.dto.ProductRequestDTO;
import com.tonyydl.springbootmall.data.po.ProductPO;
import com.tonyydl.springbootmall.service.ProductService;
import com.tonyydl.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductPO>> getProducts(
            // 查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            // 排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            // 分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1_000) @Min(0) Integer size,
            @RequestParam(defaultValue = "0") @Min(0) Integer page
    ) {
        ProductQueryParamsDTO productQueryParamsDTO = new ProductQueryParamsDTO(category, search, orderBy, sort, size, page);

        // 取得 product list
        List<ProductPO> productPOList = productService.getProducts(productQueryParamsDTO);

        // 取得 product 總數
        Integer total = productService.countProduct(productQueryParamsDTO);

        // 分頁
        Page<ProductPO> pagination = new Page<>();
        pagination.setSize(size);
        pagination.setPage(page);
        pagination.setTotal(total);
        pagination.setResults(productPOList);

        return ResponseEntity.ok().body(pagination);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductPO> getProduct(@PathVariable Integer productId) {
        ProductPO productPO = productService.getProductById(productId);

        if (productPO != null) {
            return ResponseEntity.ok().body(productPO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductPO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        Integer productId = productService.createProduct(productRequestDTO);

        ProductPO productPO = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(productPO);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductPO> updateProduct(@PathVariable Integer productId,
                                                   @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        // 檢查 product 是否存在
        ProductPO productPO = productService.getProductById(productId);

        if (productPO == null) {
            return ResponseEntity.notFound().build();
        }

        // 修改商品的數據
        productService.updateProduct(productId, productRequestDTO);

        ProductPO updatedProductPO = productService.getProductById(productId);

        return ResponseEntity.ok().body(updatedProductPO);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.noContent().build();
    }
}
