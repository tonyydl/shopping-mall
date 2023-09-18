package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.data.dto.CreateOrderRequestDTO;
import com.tonyydl.springbootmall.data.dto.OrderQueryParamsDTO;
import com.tonyydl.springbootmall.data.po.OrderPO;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParamsDTO orderQueryParamsDTO);

    List<OrderPO> getOrders(OrderQueryParamsDTO orderQueryParamsDTO);

    OrderPO getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequestDTO createOrderRequestDTO);
}
