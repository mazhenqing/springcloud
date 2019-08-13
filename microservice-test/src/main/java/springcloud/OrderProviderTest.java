package springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 订单服务
 * @author shengwu ni
 * "@EnableEurekaClient" 服务启动后会自动注册到eureka服务中心
 */
@SpringBootApplication
@MapperScan("springcloud.dao")
//@EnableCircuitBreaker //Hystrix 断路器注解
public class OrderProviderTest {

    public static void main(String[] args) {
        SpringApplication.run(OrderProviderTest.class, args);
    }
}
