package com.tonyydl.springbootmall.data.po;

import com.tonyydl.springbootmall.data.dto.OrderItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;
    private Integer quantity;
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductPO product;

    public OrderItemDTO toDTO() {
        return OrderItemDTO
                .builder()
                .orderItemId(orderItemId)
                .quantity(quantity)
                .amount(amount)
                .product(product.toDTO())
                .build();
    }
}
