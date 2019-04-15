package com.fd.mybatis.executor.result;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fengdi
 */
public interface ResultHandler {

    /**
     * @param resultSet
     * @param clazz
     * @param <T>
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     */
    <T> T handle(ResultSet resultSet, Class clazz) throws SQLException,NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
