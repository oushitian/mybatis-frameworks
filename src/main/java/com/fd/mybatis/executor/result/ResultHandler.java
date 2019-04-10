package com.fd.mybatis.executor.result;

import java.sql.SQLException;
import java.sql.Statement;

public interface ResultHandler {

    void handle(Statement statement,Class clazz) throws SQLException;
}
