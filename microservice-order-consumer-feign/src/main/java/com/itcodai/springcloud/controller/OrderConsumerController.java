package com.itcodai.springcloud.controller;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderClientService;
import com.itcodai.springcloud.util.CusAccessObjectUtil;

import com.itcodai.springcloud.util.Result;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单消费服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {
    Result result=new Result();
    /**
     * 定义在microservice-common模块的feinClient
     */
    @Resource
    private OrderClientService orderClientService;
    @PostMapping("/get/showLunbo")
    public Result showLunbo(HttpServletRequest request){
        System.out.println("feign方法--》");
        result=orderClientService.showLunbo();
        return result;
    }

    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable(value = "id") Long id) {
        return orderClientService.getOrder(id);
    }
    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderClientService.getAll();
    }
}
