package com.tonyydl.springbootmall.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private Integer size;
    private Integer page;
    private Integer total;
    private List<T> results;
}
