package com.fd.mybatis.session;

import com.fd.mybatis.binding.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @Description 配置类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName Configuration
 * @Date 2019-04-10 09:01
 */
public class Configuration {

    /**
     * 动态代理返回mapperProxy
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz,SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

    public static class MethodMapping {

        public static final String nameSpace = "com.fd.mapper.TestMapper";
    }
}
