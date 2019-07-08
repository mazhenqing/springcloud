package springcloud.service;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;
//这里原生断路器有一些问题,就是指定服务名调接口的话会一直显示短路状态,也就是
//getfallback一直被执行,而如果改成指定端口号的话,则可以正常执行,但是短路状态一直显示空
//再次记录 不深究了 有时间回头继续深究
//@Service
public class InheritOrderConsumerService extends HystrixCommand<TOrder>{

    private RestTemplate restTemplate;
    private TOrder tOrder;
    private Long id;
    //private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER"; //如果想继承HystrixCommand生成断路器，必须指定到端口号 而 MICROSERVICE-ORDER这样不行
    private static final String ORDER_PROVIDER_URL_PREFIX = "http://localhost:8004";
    //@Autowired(required = false)//降低 Autowired 检测的级别，将 Severity 的级别由之前的 error 改成 warning 或其它可以忽略的级别
//    public InheritOrderConsumerService(RestTemplate restTemplate,TOrder tOrder,Long id) {
//        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
//        this.restTemplate=restTemplate;
//        this.tOrder=tOrder;
//        this.id=id;
//    }
    public InheritOrderConsumerService(Setter setter, RestTemplate restTemplate,TOrder tOrder,Long id) {
        super(setter);
        this.restTemplate=restTemplate;
        this.tOrder=tOrder;
        this.id=id;
    }
    //controust  hystrixobservablecommand 一对多
    @Override
    protected TOrder run() throws Exception {
        return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + this.id,this.id, TOrder.class);
    }
    //resumewithfallback hystrixobservablecommand 一对多
    @Override
    protected TOrder getFallback() {
        return new TOrder().setId(11111L).setName("woaisi").setPrice(22.1).setDbSource("你能行的兄弟");
    }
}
