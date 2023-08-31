package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.dto.CreateOrderRequest;
import com.tonyydl.springbootmall.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
