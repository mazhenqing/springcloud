package com.itcodai.springcloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor//注解：表示生成带有所有属性的构造方法
@NoArgsConstructor//注解：表示生成不带参数的构方法
@Data//注解：表示生成get和set方法
@Accessors(chain = true)
public class Result {
    private Object Data;
    private String message;
    private int status;
}
