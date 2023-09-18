package com.tonyydl.springbootmall.service.impl;

import com.tonyydl.springbootmall.data.dto.BuyItemDTO;
import com.tonyydl.springbootmall.data.dto.CreateOrderRequestDTO;
import com.tonyydl.springbootmall.data.dto.OrderItemDTO;
import com.tonyydl.springbootmall.data.dto.OrderQueryParamsDTO;
import com.tonyydl.springbootmall.data.po.OrderPO;
import com.tonyydl.springbootmall.data.po.OrderItemPO;
import com.tonyydl.springbootmall.data.po.ProductPO;
import com.tonyydl.springbootmall.repository.OrderItemRepository;
import com.tonyydl.springbootmall.repository.OrderRepository;
import com.tonyydl.springbootmall.repository.ProductRepository;
import com.tonyydl.springbootmall.repository.UserRepository;
import com.tonyydl.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(UserRepository userRepository,
                            ProductRepository productRepository,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Integer countOrder(OrderQueryParamsDTO orderQueryParamsDTO) {
        return orderRepository.countByUserId(orderQueryParamsDTO.getUserId());
    }

    @Override
    public List<OrderPO> getOrders(OrderQueryParamsDTO orderQueryParamsDTO) {
        int page = orderQueryParamsDTO.getOffset() / orderQueryParamsDTO.getLimit();
        Pageable pageable = PageRequest.of(page, orderQueryParamsDTO.getLimit());
        List<OrderPO> orderList = orderRepository.findByUserIdOrderByCreatedDateDesc(orderQueryParamsDTO.getUserId(), pageable);

        for (OrderPO orderPO : orderList) {
            List<OrderItemDTO> orderItemDTOList = orderItemRepository.findOrderItemsByOrderId(orderPO.getOrderId());

            orderPO.setOrderItemDTOList(orderItemDTOList);
        }
        return orderList;
    }

    @Override
    public OrderPO getOrderById(Integer orderId) {
        OrderPO orderPO = orderRepository.findById(orderId).orElseThrow(() -> {
            log.warn("該 orderId {} 不存在", orderId);
            return new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });

        List<OrderItemDTO> orderItemDTOList = orderItemRepository.findOrderItemsByOrderId(orderId);

        orderPO.setOrderItemDTOList(orderItemDTOList);

        return orderPO;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequestDTO createOrderRequestDTO) {
        // 檢查 user 是否存在
        userRepository.findById(userId).orElseThrow(() -> {
            log.warn("userId {} 不存在", userId);
            return new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });

        int totalAmount = 0;
        List<OrderItemPO> orderItemPOList = new ArrayList<>();

        for (BuyItemDTO buyItemDTO : createOrderRequestDTO.getBuyItemDTOList()) {
            // 檢查 product 是否存在
            ProductPO productPO = productRepository.findById(buyItemDTO.getProductId()).orElseThrow(() -> {
                log.warn("商品 {} 不存在", buyItemDTO.getProductId());
                return new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });

            // 檢查 product 庫存是否足夠
            if (productPO.getStock() < buyItemDTO.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}",
                        buyItemDTO.getProductId(), productPO.getStock(), buyItemDTO.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品庫存
            int updatedStock = productPO.getStock() - buyItemDTO.getQuantity();
            productPO.setStock(updatedStock);
            productRepository.save(productPO);

            // 計算總價錢
            int amount = buyItemDTO.getQuantity() * productPO.getPrice();
            totalAmount += amount;

            // 轉換 BuyItem to OrderItem
            OrderItemPO orderItemPO = new OrderItemPO();
            orderItemPO.setProductId(buyItemDTO.getProductId());
            orderItemPO.setQuantity(buyItemDTO.getQuantity());
            orderItemPO.setAmount(amount);

            orderItemPOList.add(orderItemPO);
        }

        Date now = new Date();
        OrderPO orderPO = OrderPO.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .createdDate(now)
                .lastModifiedDate(now)
                .build();
        OrderPO savedOrderPO = orderRepository.save(orderPO);

        orderItemPOList.forEach(orderItem -> orderItem.setOrderId(savedOrderPO.getOrderId()));

        orderItemRepository.saveAll(orderItemPOList);

        return savedOrderPO.getOrderId();
    }
}
