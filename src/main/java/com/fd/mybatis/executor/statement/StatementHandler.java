package com.fd.mybatis.executor.statement;


import com.fd.mybatis.binding.MapperMethod;
import com.fd.mybatis.executor.result.ResultHandler;

import java.sql.SQLException;

/**
 * @author fengdi
 */
public interface StatementHandler {

    /**
     * 查询接口
     * @param mapperMethod
     * @param parameter
     * @param resultHandler
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T query(MapperMethod mapperMethod, String parameter, ResultHandler resultHandler) throws SQLException;

}
