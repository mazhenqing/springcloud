package spingcloud.service;

import com.itcodai.springcloud.entity.TOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class OrderConsumerService {
    @Autowired
    RestTemplate restTemplate;

    private static final String ORDER_PROVIDER_URL_PREFIX = "http://MICROSERVICE-ORDER";
    //observableExecutionMode = ObservableExecutionMode.EAGER 表示指定hystrix为hot observe 这个方法删除也是可以的
    //hot observe 该命令会在observer()调用的时候立即执行,当observeable每次被订阅的时候会重放它的行为
    //cold observe toobservable()执行之后,命令不会立即执行,只有当所有订阅者都订阅它之后才会执行
    //ignoreExceptions支持忽略指定类型异常，以下就是Hystrix会将它包装在HystrixBadRequestException中抛出,这样就不会触发后续的fallback逻辑
    //在原生的hystrix中groupkey是必须写的 commandKey则是可选的 而且默认Hystrix会让相同组名使用同一个线程池 可以通过修改组名 也就是
    //groupby 实现默认划分 但是不灵活  更细粒度划分 就需要用到threadPoolKey 直接对线程池划分与其他命令进行隔离
    //@CacheResult//定义请求缓存 如果传入的值一直这样  节省系统进程开销 这是默认的一种方式
    @CacheResult(cacheKeyMethod = "getUserById")//这个和上面比起来  是 自己定义的缓存key规则
    //还有一种请求缓存写法 getOrder(@CacheKey("id") @PathVariable Long id)  但是这种优先级会比cacheKeyMethod低  这种请求缓存写法我放在了microservice-order-consumer02-Sync
    @HystrixCommand(commandKey = "Ma",groupKey = "Zhen",threadPoolKey = "Qing",ignoreExceptions = HystrixBadRequestException.class,observableExecutionMode = ObservableExecutionMode.LAZY,fallbackMethod = "helloFallback")//HystrixCommand用于返回单个操作结果   HysreixObservableCommand对象 用于返回多个操作结果
    //以下是异步注解式写法
    public Future<TOrder> getOrder(@PathVariable Long id) {
        return new AsyncResult<TOrder>() {
            public TOrder invoke() {
               return restTemplate.postForObject(ORDER_PROVIDER_URL_PREFIX + "/provider/order/get/" + id,id, TOrder.class);
            }
        };
    }
    //当我把类型指定为long时候 会出现return type of cacheKey method must be String
    private String getUserById(Long id){
        HystrixRequestContext.initializeContext();//必须加上这个 初始化上下文 否则报异常 Request caching is not available. Maybe you need to initialize the HystrixRequestContext?
        return String.valueOf(id);
    }

    public TOrder helloFallback(@PathVariable Long id,Throwable e){
        return new TOrder().setId(1L).setName("ma").setPrice(22.1).setDbSource("aa");
    }
//下面这种写法用来
//    @HystrixCommand(fallbackMethod = "helloFallbackc")
//    public TOrder helloFallback(@PathVariable Long id){
//        throw new RuntimeException("failed");
//    }
//    public TOrder helloFallbackc(@PathVariable Long id,Throwable e){
//        //System.out.println(e.getMessage());
//        assert "failed".equals(e.getMessage());
//        return new TOrder().setId(1L).setName("ma").setPrice(22.1).setDbSource("aa");
//    }

}
