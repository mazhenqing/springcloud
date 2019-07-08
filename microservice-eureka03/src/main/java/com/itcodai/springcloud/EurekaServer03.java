package com.itcodai.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka-server
 * @author shengwu ni
 * '@EnableEurekaServer' 注解表示服务器端启动类，接受其他微服务注册进来
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer03 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer03.class, args);
    }
}
