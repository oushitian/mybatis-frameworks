package com.fd.mybatis.executor;

import com.fd.mybatis.binding.MapperMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 缓存执行器
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName CacheExecutor
 * @Date 2019-04-10 16:03
 */
@Slf4j
public class CacheExecutor implements Executor{

    private Map<String,Object> cahce = new ConcurrentHashMap<>();

    /**
     * 委派，装饰者模式
     */
    private Executor delegate;

    public CacheExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T selectOne(MapperMethod mapperMethod, String parameter) {
        //查询缓存,名字就返回
        if (cahce.containsKey(mapperMethod.getSql())) {
            System.out.println("获取一级缓存");
            return (T) cahce.get(mapperMethod.getSql());
        }
        Object obj = delegate.selectOne(mapperMethod,parameter);
        cahce.putIfAbsent(mapperMethod.getSql(),obj);
        return (T) obj;
    }
}
