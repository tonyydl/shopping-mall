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
    private Integer size;
    private Integer page;
    private Integer total;
    private List<T> results;
}
