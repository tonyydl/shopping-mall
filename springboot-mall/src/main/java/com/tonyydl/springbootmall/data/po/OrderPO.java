package com.tonyydl.springbootmall.data.po;

import com.tonyydl.springbootmall.data.dto.OrderDTO;
import com.tonyydl.springbootmall.data.dto.OrderItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderItemPO> orderItems;

    public OrderDTO toDTO() {
        List<OrderItemDTO> orderItemDTOList = orderItems
                .stream()
                .map(OrderItemPO::toDTO)
                .toList();
        return OrderDTO
                .builder()
                .orderId(orderId)
                .userId(userId)
                .totalAmount(totalAmount)
                .orderItems(orderItemDTOList)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}
