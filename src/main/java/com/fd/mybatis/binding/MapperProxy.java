package com.fd.mybatis.binding;

import com.fd.mybatis.annotations.Select;
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
        //主要流程就是根据方法名找到sql，sql可能在配置文件也可能在注解上,这里假设在注解上,理论上是个互斥的选择
        if (method.getDeclaringClass().getName().equals(Configuration.MethodMapping.nameSpace)) {
            String sql = "";
            if (method.isAnnotationPresent(Select.class)) {
                Select select = method.getAnnotation(Select.class);
                if (Objects.nonNull(select)) {
                    sql = select.value();
                }
            }
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
