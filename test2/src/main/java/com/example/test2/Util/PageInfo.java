package com.example.test2.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {

    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;
    private Long totalSize;
    private List<T> list;

}
