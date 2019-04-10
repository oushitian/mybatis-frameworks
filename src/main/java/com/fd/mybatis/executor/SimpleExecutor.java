package com.fd.mybatis.executor;

import com.fd.entity.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 简单执行者类
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName SimpleExecutor
 * @Date 2019-04-10 10:14
 */
public class SimpleExecutor implements Executor{
    @Override
    public <T> T selectOne(String sql, String parameter) {
        //声明Connection对象
        Connection con = null;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/test";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "root";
        Test test = null;
        try {
            //加载驱动程序
            Class.forName(driver);
            //获得连接
            con = DriverManager.getConnection(url,user,password);
            //创建statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(parameter));
            //获得ResultSet
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                test = new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return (T) test;
    }
}
