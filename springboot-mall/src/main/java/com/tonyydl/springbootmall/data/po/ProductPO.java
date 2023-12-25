package com.tonyydl.springbootmall.data.po;

import com.tonyydl.springbootmall.constant.ProductCategory;
import com.tonyydl.springbootmall.data.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @Column(name = "image_url")
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public ProductDTO toDTO() {
        return ProductDTO
                .builder()
                .productId(productId)
                .productName(productName)
                .category(category)
                .imageUrl(imageUrl)
                .price(price)
                .stock(stock)
                .description(description)
                .createdDate(createdDate.getTime())
                .lastModifiedDate(lastModifiedDate.getTime())
                .build();
    }
}
