package com.tonyydl.springbootmall.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.tonyydl.springbootmall.data.dto.BuyItemDTO;
import com.tonyydl.springbootmall.data.dto.CreateOrderRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 創建訂單
    @Transactional
    @Test
    public void createOrder_success() throws Exception {
        CreateOrderRequestDTO createOrderRequestDTO = CreateOrderRequestDTO.builder()
                .buyItemDTOList(Arrays.asList(
                        BuyItemDTO.builder().productId(1).quantity(5).build(),
                        BuyItemDTO.builder().productId(2).quantity(2).build()
                ))
                .build();

        String json = objectMapper.writeValueAsString(createOrderRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.order_id", notNullValue()))
                .andExpect(jsonPath("$.user_id", equalTo(1)))
                .andExpect(jsonPath("$.total_amount", equalTo(750)))
                .andExpect(jsonPath("$.order_items", hasSize(2)))
                .andExpect(jsonPath("$.created_date", notNullValue()))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));
    }

    @Transactional
    @Test
    public void createOrder_illegalArgument_emptyBuyItemList() throws Exception {
        CreateOrderRequestDTO createOrderRequestDTO = CreateOrderRequestDTO.builder().buyItemDTOList(Collections.emptyList()).build();

        String json = objectMapper.writeValueAsString(createOrderRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_userNotExist() throws Exception {
        CreateOrderRequestDTO createOrderRequest = CreateOrderRequestDTO.builder()
                .buyItemDTOList(Collections.singletonList(
                        BuyItemDTO.builder().productId(1).quantity(1).build()
                )).build();

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_productNotExist() throws Exception {
        CreateOrderRequestDTO createOrderRequest = CreateOrderRequestDTO.builder()
                .buyItemDTOList(Collections.singletonList(BuyItemDTO.builder().productId(100).quantity(1).build()))
                .build();

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_stockNotEnough() throws Exception {
        CreateOrderRequestDTO createOrderRequest = CreateOrderRequestDTO.builder()
                .buyItemDTOList(Arrays.asList(BuyItemDTO.builder().productId(1).quantity(10000).build()))
                .build();

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 查詢訂單列表
    @Test
    public void getOrders() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 1);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(2)))
                .andExpect(jsonPath("$.results[0].order_id", notNullValue()))
                .andExpect(jsonPath("$.results[0].user_id", equalTo(1)))
                .andExpect(jsonPath("$.results[0].total_amount", equalTo(100000)))
                .andExpect(jsonPath("$.results[0].order_items", hasSize(1)))
                .andExpect(jsonPath("$.results[0].created_date", notNullValue()))
                .andExpect(jsonPath("$.results[0].last_modified_date", notNullValue()))
                .andExpect(jsonPath("$.results[1].order_id", notNullValue()))
                .andExpect(jsonPath("$.results[1].user_id", equalTo(1)))
                .andExpect(jsonPath("$.results[1].total_amount", equalTo(500690)))
                .andExpect(jsonPath("$.results[1].order_items", hasSize(3)))
                .andExpect(jsonPath("$.results[1].created_date", notNullValue()))
                .andExpect(jsonPath("$.results[1].last_modified_date", notNullValue()));
    }

    @Test
    public void getOrders_pagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 1)
                .param("size", "2")
                .param("page", "2");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }

    @Test
    public void getOrders_userHasNoOrder() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 2);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }

    @Test
    public void getOrders_userNotExist() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 100);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }
}