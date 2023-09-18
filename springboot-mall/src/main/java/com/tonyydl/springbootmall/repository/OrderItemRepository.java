package com.tonyydl.springbootmall.repository;

import com.tonyydl.springbootmall.data.dto.OrderItemDTO;
import com.tonyydl.springbootmall.data.po.OrderItemPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemPO, Integer> {
    @Query("SELECT new com.tonyydl.springbootmall.data.dto.OrderItemDTO(oi.orderItemId, oi.orderId, oi.productId, oi.quantity, oi.amount, p.productName, p.imageUrl) " +
            "FROM OrderItemPO AS oi " +
            "LEFT JOIN FETCH ProductPO p ON oi.productId = p.productId " +
            "WHERE oi.orderId = :orderId")
    List<OrderItemDTO> findOrderItemsByOrderId(Integer orderId);
}
