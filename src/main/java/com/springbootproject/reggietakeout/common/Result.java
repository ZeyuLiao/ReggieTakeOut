package com.springbootproject.reggietakeout.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;  // 编码：1成功。0和其他数字失败
    private String msg;  // 错误信息
    private T data; // 数据
    private Map map = new HashMap();  // 动态数据

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.code = 1;  //成功状态码
        r.data = data;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.msg = msg; //设置错误信息
        r.code = 0;  //默认失败状态码，后期我们可以根据自己的需求来设置其他状态码
        return r;
    }

    public Result<T> add(String msg, String value) {
        this.map.put(msg, value);
        return this;
    }
}
