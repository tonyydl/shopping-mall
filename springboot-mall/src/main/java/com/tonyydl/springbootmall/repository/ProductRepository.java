package com.tonyydl.springbootmall.repository;

import com.tonyydl.springbootmall.constant.ProductCategory;
import com.tonyydl.springbootmall.data.po.ProductPO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductPO, Integer> {

    Integer countByCategoryAndProductNameContainingIgnoreCase(ProductCategory category, String search);

    @Query("SELECT p FROM ProductPO p " +
            "WHERE (:category IS NULL OR p.category = :category) " +
            "AND (:search IS NULL OR p.productName LIKE CONCAT('%', :search, '%'))")
    List<ProductPO> findProductsByCategoryAndProductNameContaining(
            ProductCategory category,
            String search,
            Pageable pageable);
}
