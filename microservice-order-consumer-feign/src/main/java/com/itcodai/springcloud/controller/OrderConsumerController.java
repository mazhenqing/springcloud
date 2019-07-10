package com.itcodai.springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderClientService;
import org.springframework.web.bind.annotation.*;

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
     * 定义在microservice-common模块的feinClient
     */
    @Resource
    private OrderClientService orderClientService;

    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable(value = "id") Long id) {
        return orderClientService.getOrder(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderClientService.getAll();
    }
}
