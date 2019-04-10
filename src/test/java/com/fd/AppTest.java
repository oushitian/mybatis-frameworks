package com.fd;

import com.fd.mapper.TestMapper;
import com.fd.mybatis.executor.SimpleExecutor;
import com.fd.mybatis.session.Configuration;
import com.fd.mybatis.session.SqlSession;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testMybatis()
    {
        SqlSession sqlSession = new SqlSession(new Configuration(),new SimpleExecutor());
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        com.fd.entity.Test test = testMapper.selectByPrimaryKey(2);
        System.out.println(test.getName());
    }
}
