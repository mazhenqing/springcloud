package springcloud.service.impl;


import com.itcodai.springcloud.entity.TOrder;

import org.springframework.stereotype.Service;
import springcloud.dao.OrderMapper;
import springcloud.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override public TOrder findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override public List<TOrder> findAll() {
        return orderMapper.findAll();
    }
}
