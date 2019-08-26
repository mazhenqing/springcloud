package com.itcodai.springcloud.service;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.util.Result;

import java.util.List;

public interface OrderService {
    //查询首页下方基本信息
    Result showBelowInformation();
    //显示轮播图
    Result showLunbo();
    TOrder findById(Long id);
    List<TOrder> findAll();
}
