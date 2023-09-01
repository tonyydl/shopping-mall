package com.tonyydl.springbootmall.dao;

import com.tonyydl.springbootmall.dto.OrderQueryParams;
import com.tonyydl.springbootmall.model.Order;
import com.tonyydl.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
