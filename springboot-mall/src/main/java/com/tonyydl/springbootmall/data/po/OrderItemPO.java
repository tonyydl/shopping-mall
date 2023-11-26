package com.tonyydl.springbootmall.data.po;

import jakarta.persistence.*;
import lombok.*;

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
}
