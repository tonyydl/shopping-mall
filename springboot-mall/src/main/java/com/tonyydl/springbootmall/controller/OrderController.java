package com.tonyydl.springbootmall.controller;

import com.tonyydl.springbootmall.data.dto.CreateOrderRequestDTO;
import com.tonyydl.springbootmall.data.dto.OrderQueryParamsDTO;
import com.tonyydl.springbootmall.data.po.OrderPO;
import com.tonyydl.springbootmall.service.OrderService;
import com.tonyydl.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<OrderPO>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        OrderQueryParamsDTO orderQueryParamsDTO = OrderQueryParamsDTO.builder()
                .userId(userId)
                .limit(limit)
                .offset(offset)
                .build();

        // 取得 order list
        List<OrderPO> orderList = orderService.getOrders(orderQueryParamsDTO);

        // 取得 order 總數
        Integer count = orderService.countOrder(orderQueryParamsDTO);

        // 分頁
        Page<OrderPO> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequestDTO createOrderRequestDTO) {

        Integer orderId = orderService.createOrder(userId, createOrderRequestDTO);

        OrderPO orderPO = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderPO);
    }
}
