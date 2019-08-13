package com.itcodai.springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单消费服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {

    /**
     * 订单服务提供者模块的 url 前缀
     */
//    private static final String ORDER_PROVIDER_URL_PREFIX = "http://localhost:8001";
    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        //return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id,id, TOrder.class);
        ResponseEntity<TOrder> responseEntity =restTemplate.postForEntity(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id,id, TOrder.class);
        return responseEntity.getBody();
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/list", List.class);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/discovery", Object.class);
    }
}
