package com.fd.mybatis.factory;

public interface ObjectFactory {

    <T> T create(Class<T> clazz);

}
