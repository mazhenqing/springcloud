package spingcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spingcloud.service.OrderConsumerService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 订单消费服务
 * @author zhenqing ma
 */
@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {

    /**
     * 订单服务提供者模块的 url 前缀
     */
//    private static final String ORDER_PROVIDER_URL_PREFIX = "http://localhost:8001";
    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    OrderConsumerService orderConsumerService;
    //加在消费方的断路器  将它分离在service上
    @GetMapping("/get/{id}")
    /*
    InterruptedException如果interrupt在计算完成之前在等待的线程上调用，则会抛出。
    ExecutionException如果涉及的计算（Task在本例中）抛出异常本身，将会抛出。
     */
    //Future表示一个可能还没有完成的异步任务的结果，针对这个结果可以添加Callback以便在任务执行成功或失败后作出相应的操作
    public TOrder getOrder(@PathVariable Long id) throws ExecutionException, InterruptedException{
        Future<TOrder> torderFuture = orderConsumerService.getOrder(id);
        return  torderFuture.get();
    }


    @SuppressWarnings("unchecked")
    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/list", List.class);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/discovery", Object.class);
    }
}
