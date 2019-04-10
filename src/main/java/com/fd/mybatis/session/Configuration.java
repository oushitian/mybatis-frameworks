package com.fd.mybatis.session;

import com.fd.mapper.TestMapper;
import com.fd.mybatis.binding.MapperMethod;
import com.fd.mybatis.binding.MapperRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 配置类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName Configuration
 * @Date 2019-04-10 09:01
 */
public final class Configuration {

    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);
    public static final Map<String, MapperMethod> MapperMethodMap = new ConcurrentHashMap<>();

    public static Map<String, MapperMethod> getMapperMethodMap() {
        return MapperMethodMap;
    }

    public Configuration() {
        //这里其实是解析xml的,暂时写死
        addMapper(TestMapper.class);
    }

    /**
     * 动态代理返回mapperProxy
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz,SqlSession sqlSession) {
        return mapperRegistry.getMapper(clazz,sqlSession);
    }

    /**
     * 注册mapper
     * @param clazz
     * @param <T>
     */
    public <T> void addMapper(Class<T> clazz) {
        mapperRegistry.addMapper(clazz);
    }
}
