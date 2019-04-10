package com.fd.mybatis.executor;

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
    public <T> T selectOne(String sql, String parameter) {
        //查询缓存,名字就返回
        if (cahce.containsKey(sql)) {
            System.out.println("获取一级缓存");
            return (T) cahce.get(sql);
        }
        Object obj = delegate.selectOne(sql,parameter);
        cahce.putIfAbsent(sql,obj);
        return (T) obj;
    }
}
