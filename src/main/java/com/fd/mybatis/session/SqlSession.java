package com.fd.mybatis.session;


import com.fd.mybatis.executor.Executor;

/**
 * @Description sqlSession
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName SqlSession
 * @Date 2019-04-10 08:54
 */
public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 根据传入的类型获取mapper
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    /**
     * 根据sql和参数查询数据库
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String sql,String parameter) {
        return executor.selectOne(sql,parameter);
    }
}
