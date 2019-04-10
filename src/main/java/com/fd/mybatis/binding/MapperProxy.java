package com.fd.mybatis.binding;

import com.fd.mybatis.session.Configuration;
import com.fd.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Description 代理类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName MapperProxy
 * @Date 2019-04-10 09:09
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperMethod mapperMethod = Configuration.getMapperMethodMap().get(method.getDeclaringClass().getName());
        if (Objects.nonNull(mapperMethod)) {
            String sql = mapperMethod.getSql();
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
