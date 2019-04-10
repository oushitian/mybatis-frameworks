package com.fd.mybatis.binding;

import com.fd.entity.Test;
import com.fd.mybatis.annotations.Select;
import com.fd.mybatis.session.Configuration;
import com.fd.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description MapperRegistry注册类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName MapperRegistry
 * @Date 2019-04-10 11:52
 */
@Slf4j
public class MapperRegistry {

    private Map<Class, MapperProxyFactory> map = new ConcurrentHashMap<>();

    private Configuration configuration;

    public MapperRegistry(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * 获取mapper
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        MapperProxyFactory mapperProxyFactory = map.get(clazz);
        if (Objects.isNull(mapperProxyFactory)) {
            log.info("未注册");
            return null;
        }
        return (T) mapperProxyFactory.newInstance(sqlSession);
    }

    /**
     * 注册mapper
     * @param clazz
     */
    public <T> void addMapper(Class<T> clazz){
        if (clazz.isInterface()) {
            //防止重复注册
            if (hasMapper(clazz)) {
                log.info("不能重复注册");
                return;
            }
            map.putIfAbsent(clazz,new MapperProxyFactory(clazz));
            Arrays.asList(clazz.getMethods()).forEach(method -> {
                if (method.isAnnotationPresent(Select.class)) {
                    MapperMethod mapperMethod = new MapperMethod();
                    mapperMethod.setMethod(method);
                    mapperMethod.setEntity(Test.class);
                    mapperMethod.setSql(method.getAnnotation(Select.class).value());
                    Configuration.MapperMethodMap.putIfAbsent(clazz.getName(), mapperMethod);
                }
            });
        }
    }

    private boolean hasMapper(Class clazz) {
        return map.containsKey(clazz);
    }
}
