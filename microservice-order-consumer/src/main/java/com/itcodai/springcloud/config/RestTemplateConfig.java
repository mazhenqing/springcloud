package com.itcodai.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 * @author shengwu ni
 */
@Configuration
public class RestTemplateConfig {

    /**
     * '@LoadBalanced'注解表示使用Ribbon实现客户端负载均衡
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    /**
//     * 指定其他负载均衡策略 注：使用自定义负载均衡时，该方法需要去掉
//     * @return IRule
//     */
//    @Bean
//    public IRule myRule() {
//        // 指定策略：随机
//        return new RetryRule();
//    }
}
