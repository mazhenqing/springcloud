package com.itcodai.springcloud.service.impl;

import com.itcodai.springcloud.dao.OrderMapper;
import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderService;
import com.itcodai.springcloud.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    Result result=new Result();
    @Resource
    private OrderMapper orderMapper;
    /**
     * 查询首页下方基本信息
     * @return
     */
    @Override
    public Result showBelowInformation() {
        Basic basic=orderMapper.showBelowInformation();
        result.setStatus(0).setData(basic).setMessage("查询成功");
        return result;
    }
    /**
     * 显示轮播图
     * @return
     */
    @Override
    public Result showLunbo() {
        result.setStatus(0).setData(orderMapper.showLunbo());
        return result;
    }

    @Override public TOrder findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override public List<TOrder> findAll() {
        return orderMapper.findAll();
    }
}
