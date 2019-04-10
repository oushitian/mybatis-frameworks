package com.fd.mybatis.binding;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Description 方法语句映射类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName MapperMethod
 * @Date 2019-04-10 14:11
 */
@Data
public class MapperMethod {

    private String sql;
    private Method method;

}
