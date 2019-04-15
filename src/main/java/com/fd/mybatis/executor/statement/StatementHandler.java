package com.fd.mybatis.executor.statement;


import com.fd.mybatis.binding.MapperMethod;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author fengdi
 */
public interface StatementHandler {

    /**
     * 查询接口
     * @param mapperMethod
     * @param parameter
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T query(MapperMethod mapperMethod, String parameter) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
