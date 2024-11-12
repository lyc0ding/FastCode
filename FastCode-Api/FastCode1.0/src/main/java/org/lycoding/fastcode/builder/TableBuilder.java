package org.lycoding.fastcode.builder;


import lombok.extern.slf4j.Slf4j;
import org.lycoding.fastcode.bean.Constants;
import org.lycoding.fastcode.bean.TableInfo;
import org.lycoding.fastcode.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class TableBuilder {
    private static Connection connection=null;
//    private static final Logger logger = LoggerFactory.getLogger(TableBuilder.class);

    private static final String SQL="show table status";

    static {
        String driver= PropertiesUtils.getValue("db.driverName");
        String url= PropertiesUtils.getValue("db.url");
        String user= PropertiesUtils.getValue("db.username");
        String password= PropertiesUtils.getValue("db.password");

        try {
            System.out.println("开始连接数据库");
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            System.err.println("数据库连接失败");
        }
    }

    public static void getTable() throws SQLException {
        PreparedStatement ps=null;
        ResultSet rs=null;

        List<TableInfo> tableInfoList = new ArrayList();

        try {
            ps = connection.prepareStatement(SQL);
            rs=ps.executeQuery();

            while (rs.next()){
//                解析表信息，添加到列表
                String tableName=rs.getString("name");
                String comment=rs.getString("comment");

                String beanName=tableName;
//                忽略表名前缀：tb_ 、 sys_ ……
                if(Constants.IGNORE_TABLE_PREFIX){
                    beanName=tableName.substring(beanName.indexOf("_")+1);
                }

                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setTableComment(comment);
                tableInfo.setBeanName(beanName);


                tableInfoList.add(tableInfo);
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

    public String processField(String field,Boolean upCaseFiledName){
        return null;
    }
}
