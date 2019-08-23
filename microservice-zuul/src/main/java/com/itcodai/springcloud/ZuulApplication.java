package com.itcodai.springcloud;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itcodai.springcloud.config.AccessFilter;
import com.itcodai.springcloud.config.DidiFilterProcessor;
import com.itcodai.springcloud.config.ErrorFilter;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        //启动专门为post过滤器引起的异常类
        //自定义的核心处理器 必须放在springboot的启动类里面 这个是专门优化post过滤器的
        FilterProcessor.setProcessor(new DidiFilterProcessor());
        SpringApplication.run(ZuulApplication.class, args);
    }
    //用来给项目制定版本规则的
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
    //启动zuul的安全校验和验证
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
    //出来来自zuul的pre、route、post过滤器返回的异常 统一处理并返回
    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
    //对返回的异常信息进行定制或者同一格式
    @Bean
    public DefaultErrorAttributes defaultErrorAttributes(){
        return new DefaultErrorAttributes();
    }
}
