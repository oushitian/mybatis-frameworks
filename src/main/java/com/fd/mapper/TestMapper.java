package com.fd.mapper;

import com.fd.entity.Test;
import com.fd.mybatis.annotations.Select;

public interface TestMapper {

    /**
     * 根据注解查询实体类信息
     * @param id
     * @return
     */
    @Select("select * from test where id = ?")
    Test selectByPrimaryKey(Integer id);

}
