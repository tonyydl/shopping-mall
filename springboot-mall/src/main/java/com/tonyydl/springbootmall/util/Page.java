package com.tonyydl.springbootmall.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> results;
}
