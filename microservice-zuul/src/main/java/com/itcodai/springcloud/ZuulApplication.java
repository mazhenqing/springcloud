package com.itcodai.springcloud;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itcodai.springcloud.config.AccessFilter;
import com.itcodai.springcloud.config.ErrorFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
}
