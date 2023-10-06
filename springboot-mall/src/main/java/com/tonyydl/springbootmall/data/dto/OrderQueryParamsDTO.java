package com.tonyydl.springbootmall.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParamsDTO {
    private Integer userId;
    private Pageable pageable;
}
