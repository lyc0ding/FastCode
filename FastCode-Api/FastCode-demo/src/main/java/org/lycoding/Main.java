package org.lycoding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lycoding
 * @DateTime: 2024/11/11 0:55
 **/
@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
        log.info("项目启动成功!!!");
    }
}
