package com.tonyydl.springbootmall.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParamsDTO {
    private Integer userId;
    private Integer size;
    private Integer page;
}
