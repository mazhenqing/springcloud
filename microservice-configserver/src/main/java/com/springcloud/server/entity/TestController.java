package com.springcloud.server.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /**
     * 获取application.properties自定义值 需要注入(Autowired)
     * 不能通过实例化获取(new 对象形式)
     */
    @Autowired
    Test ftest;
    @GetMapping("/test")
    public Test test(){
        return ftest;
    }
}
