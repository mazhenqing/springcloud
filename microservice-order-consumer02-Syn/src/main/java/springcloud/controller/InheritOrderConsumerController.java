package springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.service.InheritOrderConsumerService;
import springcloud.service.OrderConsumerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单消费服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/inconsumer/order")
public class InheritOrderConsumerController {
//不用注解hystrix 而是手写类的方式
//加在消费方的断路器  将它分离在service上
//withExecutionTimeoutInMilliseconds 默认超时时间是1秒 下面方法设置了2秒
    //private TOrder tOrder1;
    @GetMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        TOrder tOrder=new InheritOrderConsumerService(HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("myfirstHy")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)),
                new RestTemplate(),new TOrder() ,id).execute();
        return tOrder;
    }
}
