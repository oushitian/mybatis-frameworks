package com.fd.mybatis.executor;

import com.fd.mybatis.binding.MapperMethod;

/**
 * 执行器接口
 * @author fengdi
 */
public interface Executor {

    /**
     * @param mapperMethod
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(MapperMethod mapperMethod, String parameter);
}
