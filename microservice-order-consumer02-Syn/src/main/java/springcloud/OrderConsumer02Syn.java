package springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//这是一种写法 还有一种在microservice-order-consumer里
@SpringBootApplication
@EnableEurekaClient  //eureka-client端
@EnableCircuitBreaker //开启hystrix断路器
//以上三个注解可以被一个注解替换
//@SpringCloudApplication
public class OrderConsumer02Syn {
    //在启动类中创建restTemplate的springbean实例
    @Bean
    @LoadBalanced //开启客户端负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    /**
     *
     * RoundRobinRule：轮询。人人有份，一个个来！
     RandomRule：随机。拼人品了！
     AvailabilityFilteringRule：先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，以及并发连接数超过阈值的服务，剩下的服务，使用轮询策略。
     WeightedResponseTimeRule：根据平均响应时间计算所有服务的权重，响应越快的服务权重越高，越容易被选中。一开始启动时，统计信息不足的情况下，使用轮询。
     RetryRule：先轮询，如果获取失败则在指定时间内重试，重新轮询可用的服务。
     BestAvailableRule：先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务。 选出最空闲实例
     ZoneAvoidanceRule：复合判断 server 所在区域的性能和 server 的可用性选择服务器
     ClientConfigEnabledRoundRobinRule 高级策略都需要继承它 本身不会被直接使用
     PredicateBasedRule 策略 先过滤掉一部分实例 ,然后开始轮询
     * 指定其他负载均衡策略
     * @return IRule
     */
    @Bean
    public IRule myRule() {
        // 指定重试策略：随机策略
        return new RandomRule();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumer02Syn.class, args);
    }
}
