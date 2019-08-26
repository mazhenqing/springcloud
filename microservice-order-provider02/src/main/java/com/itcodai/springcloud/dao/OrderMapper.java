package com.itcodai.springcloud.dao;

import com.itcodai.springcloud.entity.Basic;
import com.itcodai.springcloud.entity.TOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    //查询首页下方基本信息
    Basic showBelowInformation();
    @Select("select * from t_order where id = #{id}")
    TOrder findById(Long id);

    @Select("select * from t_order")
    List<TOrder> findAll();

    //显示轮播图
    Basic showLunbo();
}
