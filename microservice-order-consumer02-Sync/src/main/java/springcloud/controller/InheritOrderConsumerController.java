package springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springcloud.service.InheritOrderConsumerService;

import java.util.concurrent.Future;

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
//execute()是同步执行  queue（）是异步执行
//Future<String> fs = new CommandHelloWorld("World").queue();
//String s = fs.get();
    @PostMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) throws Exception{
        Future<TOrder> tOrder=new InheritOrderConsumerService(HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("myfirstHy")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)),
                new RestTemplate(),new TOrder() ,id).queue();
        return tOrder.get();
    }
}
