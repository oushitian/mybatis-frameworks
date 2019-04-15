package com.fd.mybatis.executor.statement;


import com.fd.mybatis.binding.MapperMethod;
import com.fd.mybatis.executor.result.ResultHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description SimpleStatementHandler
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName SimpleStatementHandler
 * @Date 2019-04-10 16:51
 */
public class SimpleStatementHandler extends BaseStatementHandler{

    public SimpleStatementHandler(ResultHandler resultHandler) {
        super(resultHandler);
    }

    @Override
    public <T> T query(MapperMethod mapperMethod, String parameter) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Connection con = getConnection();
        //创建statement
        PreparedStatement statement = con.prepareStatement(mapperMethod.getSql());
        statement.setInt(1,Integer.parseInt(parameter));
        statement.executeQuery();
        return resultHandler.handle(statement.getResultSet(),mapperMethod.getEntity());
    }

    private Connection getConnection() {
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
        try {
            //加载驱动程序
            Class.forName(driver);
            //获得连接
            con = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }
}
