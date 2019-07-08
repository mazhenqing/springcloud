package springcloud.service;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderConsumerService {
    @Autowired
    RestTemplate restTemplate;

    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";
    @HystrixCommand(fallbackMethod = "helloFallback")//HystrixCommand用于返回单个操作结果   HysreixObservableCommand对象 用于返回多个操作结果
    public TOrder getOrder(@PathVariable Long id) {
        return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id,id, TOrder.class);
    }

    public TOrder helloFallback(@PathVariable Long id){
        return new TOrder().setId(1L).setName("ma").setPrice(22.1).setDbSource("aa");
    }
}
