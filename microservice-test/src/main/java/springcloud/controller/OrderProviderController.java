package springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springcloud.service.OrderService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * 订单服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/provider/order")
public class OrderProviderController {
    Locale locale;
    @Resource
    private OrderService orderService;


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProviderController.class);

    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable(value = "id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderService.findAll();
    }
}
