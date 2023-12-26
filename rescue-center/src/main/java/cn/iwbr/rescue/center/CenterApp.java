package cn.iwbr.rescue.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: 注册中心启动类
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2023/12/22
 */
@EnableEurekaServer
@SpringBootApplication
public class CenterApp {

    public static void main(String[] args) {
        SpringApplication.run(CenterApp.class, args);
    }

    public CenterApp() {}
}
