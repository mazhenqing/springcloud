package springcloud.service;

import com.itcodai.springcloud.entity.TOrder;

import com.netflix.hystrix.HystrixCommand;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@Service
public class InheritOrderConsumerService extends HystrixCommand<TOrder>{

    private RestTemplate restTemplate;
    private TOrder tOrder;
    private Long id;
//    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";
    private static final String ORDER_PROVIDER_URL_PREFIX = "http://localhost:8004";
    //@Autowired(required = false)//降低 Autowired 检测的级别，将 Severity 的级别由之前的 error 改成 warning 或其它可以忽略的级别
    public InheritOrderConsumerService(Setter setter, RestTemplate restTemplate,Long id) {
        super(setter);
        this.restTemplate=restTemplate;
        this.id=id;
    }
    @Override
    protected TOrder run() throws Exception {
        return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/"+id,id, TOrder.class);
    }

    @Override
    protected TOrder getFallback() {
        return tOrder.setId(11111L).setName("woaisi").setPrice(22.1).setDbSource("你能行的兄弟");
    }
}