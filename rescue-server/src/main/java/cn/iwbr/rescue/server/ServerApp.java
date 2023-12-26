package cn.iwbr.rescue.server;

import org.apache.catalina.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: server启动类
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2023/12/25
 */
@SpringBootApplication
@EnableEurekaClient
public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
