package com.fd.mybatis.executor.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description 默认实现类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName DefaultResultHandler
 * @Date 2019-04-10 17:12
 */
public class DefaultResultHandler implements ResultHandler{
    @Override
    public void handle(Statement statement,Class clazz) throws SQLException {
        //获得ResultSet
        ResultSet rs = statement.getResultSet();

    }
}
