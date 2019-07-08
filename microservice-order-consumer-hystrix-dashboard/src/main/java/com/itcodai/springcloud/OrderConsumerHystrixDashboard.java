package com.itcodai.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard //Hystrix Dashboard 监控需要使用的注解
public class OrderConsumerHystrixDashboard {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerHystrixDashboard.class, args);
    }
}
