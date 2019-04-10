package com.fd.mybatis.executor;

/**
 * 执行器接口
 * @author fengdi
 */
public interface Executor {

    /**
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String sql, String parameter);
}
