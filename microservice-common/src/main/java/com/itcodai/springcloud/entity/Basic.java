package com.itcodai.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor//注解：表示生成带有所有属性的构造方法
@NoArgsConstructor//注解：表示生成不带参数的构方法
@Data//注解：表示生成get和set方法
@Accessors(chain = true)
public class Basic {
    private int id;
    private String wheel_plantingone;
    private String wheel_plantingtwo;
    private String wheel_plantingthree;
    private String wheel_plantingfour;
    private String rotary_title;
    private String address;
    private String telephone;
    private String mailbox;
    private String about_us;
    private String recent_projects;
    private String latest_news;
}
