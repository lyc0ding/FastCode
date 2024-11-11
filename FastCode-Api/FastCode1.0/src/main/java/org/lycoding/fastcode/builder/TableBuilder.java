package org.lycoding.fastcode.builder;


import org.lycoding.fastcode.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class TableBuilder {
    private static Connection connection=null;
    private static final Logger log= LoggerFactory.getLogger(TableBuilder.class);

    private static final String SQL="show table status";

    static {
        String driver= PropertiesUtils.getValue("db.driverName");
        String url= PropertiesUtils.getValue("db.url");
        String user= PropertiesUtils.getValue("db.username");
        String password= PropertiesUtils.getValue("db.password");

        try {
            log.warn("开始连接数据库");
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            log.info("数据库连接失败");
        }
    }

    public static void getTable() throws SQLException {
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(SQL);
            rs=ps.executeQuery();

            while (rs.next()){
                String tableName=rs.getString("name");
                log.info("name={name}",tableName);
                String comment=rs.getString("comment");
                log.info("comment={comment}",comment);

            }
        } catch (Exception e) {
            new RuntimeException("获取表失败");
        }
        finally {
            if (rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        getTable();
    }
}
