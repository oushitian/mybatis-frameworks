package com.fd.mybatis.binding;

import com.fd.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @Description 代理工程类，用于负责生产代理
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName MappingProxyFactory
 * @Date 2019-04-10 13:25
 */
public class MapperProxyFactory<T> {

    private final Class<T> clazz;

    public MapperProxyFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T newInstance(SqlSession sqlSession){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

}
