package com.itcodai.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单实体
 */
@AllArgsConstructor//注解：表示生成带有所有属性的构造方法
@NoArgsConstructor//注解：表示生成不带参数的构方法
@Data//注解：表示生成get和set方法
@Accessors(chain = true)
public class TOrder {

    /**
     * &#x4e3b;&#x952e;id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 所存的数据库名称
     */
    private String dbSource;
}
