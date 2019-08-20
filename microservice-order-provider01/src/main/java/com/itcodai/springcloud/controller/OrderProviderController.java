package com.itcodai.springcloud.controller;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderService;
import com.itcodai.springcloud.util.Result;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/provider/order")
public class OrderProviderController {
    Result result=new Result();
    @Resource
    private OrderService orderService;
    @Resource
    private EurekaClient client;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProviderController.class);
    //查询轮播图
    @PostMapping("/get/lunbo")
    public Result showLunbo(){
        System.out.println("这是走1了");
        result=orderService.showLunbo();
        return result;
    }
    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable(value = "id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/discovery")
    public Object discovery() {
        // 获取Eureka中所有的服务节点
        List<Application> applications = client.getApplications().getRegisteredApplications();
        if (applications != null) {
            for (Application application : applications) {
                // 对外暴露的服务名称
                String name = application.getName();
                // 只看订单服务信息
                if ("MICROSERVICE-ORDER".equals(name)) {
                    // 服务有多少个实例，比如订单服务可能部署了多个，有多个订单服务注册到了eureka
                    List<InstanceInfo> instances = application.getInstances();
                    LOGGER.info("所有的订单服务：{}", instances);
                    if (instances != null) {
                        for (InstanceInfo info : instances) {
                            LOGGER.info("服务id：{}", info.getInstanceId());
                            LOGGER.info("服务主机：{}", info.getHostName());
                            LOGGER.info("服务端口：{}", info.getPort());
                        }
                    }
                    return instances;
                }
            }
        }
        return null;
    }
}
