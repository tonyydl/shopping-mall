package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.dto.CreateOrderRequest;
import com.tonyydl.springbootmall.dto.OrderQueryParams;
import com.tonyydl.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
