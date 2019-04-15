package com.fd.mybatis.executor.result;

import com.fd.mybatis.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 默认实现类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName DefaultResultHandler
 * @Date 2019-04-10 17:12
 */
public class DefaultResultHandler implements ResultHandler{

    private final static char A = 'a';
    private final static char B = 'b';

    @Override
    public <T> T handle(ResultSet resultSet,Class clazz) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = new DefaultObjectFactory().create(clazz);
        if (resultSet.next()) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0 ; i < fields.length ; i ++) {
                setValue(obj,fields[i],resultSet,i);
            }
        }
        return (T)obj;
    }

    private void setValue(Object obj, Field field, ResultSet resultSet, int i) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Method setMethod = obj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(obj, getResult(field,resultSet));
    }

    private Object getResult(Field field, ResultSet resultSet) throws SQLException {
        Class<?> type = field.getType();
        if(Integer.class == type){
            return resultSet.getInt(field.getName());
        }
        if(String.class == type){
            return resultSet.getString(field.getName());
        }
        return resultSet.getString(field.getName());

    }

    private String upperCapital(String name) {
        char[] chars = name.toCharArray();
        if (chars[0] >= A && chars[0] <= B) {
            chars[0] = (char)(chars[0] - 32);
            return new String(chars);
        } else {
            return name.substring(0,1).toUpperCase().concat(name.substring(1).toLowerCase());
        }
    }
}
