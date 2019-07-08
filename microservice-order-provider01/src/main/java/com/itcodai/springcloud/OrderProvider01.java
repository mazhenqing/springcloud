package com.itcodai.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 订单服务
 * @author shengwu ni
 * "@EnableEurekaClient" 服务启动后会自动注册到eureka服务中心
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.itcodai.springcloud.dao")
//@EnableCircuitBreaker //Hystrix 断路器注解
public class OrderProvider01 {

    public static void main(String[] args) {
        SpringApplication.run(OrderProvider01.class, args);
    }
}
