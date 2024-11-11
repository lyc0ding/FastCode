package org.lycoding.fastcode.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取配置文件内容
 * 数据库配置信息
 */

public class PropertiesUtils {
    private static Properties properties = new Properties();
    private static ConcurrentHashMap<String,String> PROPS_MAP = new ConcurrentHashMap<>();

    static{

        // 指定配置文件的路径
        String configFilePath = "application.properties";
        InputStream input =null;
        try {
            input = PropertiesUtils.class.getClassLoader().getResourceAsStream(configFilePath);
            // 加载属性文件
            properties.load(input);
            //迭代器
            Iterator<Object> iterator = properties.keySet().iterator();

            while(iterator.hasNext()){
                String key= (String) iterator.next();
                PROPS_MAP.put(key,properties.getProperty(key));
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            //关闭输入流
            if (input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getValue(String key){
        return PROPS_MAP.get(key);
    }

    public static void main(String[] args) {
        System.out.println("db.username:"+getValue("db.username"));
    }
}
