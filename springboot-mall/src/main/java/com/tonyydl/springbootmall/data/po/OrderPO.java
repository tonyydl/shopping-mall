package com.tonyydl.springbootmall.data.po;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class OrderPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "total_amount")
    private Integer totalAmount;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @OneToMany
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderItemPO> orderItems;
}
