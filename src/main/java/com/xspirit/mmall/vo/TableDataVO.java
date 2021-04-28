package com.xspirit.mmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class TableDataVO<T> {
    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;
}
