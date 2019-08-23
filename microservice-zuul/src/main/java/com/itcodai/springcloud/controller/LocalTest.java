package com.itcodai.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local")
public class LocalTest {
    @RequestMapping("/hello")
    public String LocalController(){
        return "测试一下zuul转发(forward)本地功能";
    }
}
