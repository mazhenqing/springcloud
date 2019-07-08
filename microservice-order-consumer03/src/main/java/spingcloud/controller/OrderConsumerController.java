package spingcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单消费服务
 * @author shengwu ni
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
//get请求的一中写法
//    @GetMapping("/get/{id}")
//    public TOrder getOrder(@PathVariable Long id) {
//        return restTemplate.getForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id, TOrder.class);
//    }
//POST请求的一种写法 其中有三种 POSTforEntity POSTforObject Postforlocation
    @GetMapping("/get/{id}")
    //这是注解同步执行
    @HystrixCommand(fallbackMethod = "getOrderfallBack")//HystrixCommand用于返回单个操作结果   HysreixObservableCommand对象 用于返回多个操作结果
    public TOrder getOrder(@PathVariable Long id) {
        return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id,id, TOrder.class);
    }
    public TOrder getOrderfallBack(@PathVariable Long id) {
        return new TOrder().setId(1L).setName("马").setPrice(22.1).setDbSource("测试");
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
