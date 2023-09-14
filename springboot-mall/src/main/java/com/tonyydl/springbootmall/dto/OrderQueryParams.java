package com.tonyydl.springbootmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParams {
    private Integer userId;
    private Integer limit;
    private Integer offset;
}
