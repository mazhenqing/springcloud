package com.itcodai.springcloud.service;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.util.Result;

import java.util.List;

public interface OrderService {
    Result showLunbo();
    TOrder findById(Long id);
    List<TOrder> findAll();
}
