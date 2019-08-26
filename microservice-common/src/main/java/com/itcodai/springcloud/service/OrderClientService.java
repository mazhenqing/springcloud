package com.itcodai.springcloud.service;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * feign客户端
 * @author shengwu ni
 */
//@FeignClient(value = "MICROSERVICE-ORDER")
@FeignClient(value = "MICROSERVICE-ORDER", fallbackFactory = OrderClientServiceFallbackFactory.class)
public interface OrderClientService {
    @PostMapping("/provider/order/show/belowinformation")
    Result showBelowInformation();
    @PostMapping("/provider/order/get/lunbo")
    Result showLunbo();
    @PostMapping("/provider/order/get/{id}")
    //这里必须要用@PathVariable(value = "id")  不能用@PathVariable这种形式 否则报错
    //org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'orderConsumerController': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.itcodai.springcloud.service.OrderClientService': FactoryBean threw exception on object creation; nested exception is java.lang.IllegalStateException: PathVariable annotation was empty on param 0.
    TOrder getOrder(@PathVariable(value = "id") Long id);

    @GetMapping("/provider/order/get/list")
    List<TOrder> getAll();
}
