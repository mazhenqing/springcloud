package springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springcloud.controller.OrderProviderController;

import javax.annotation.PostConstruct;

//@Component
//public class ConfigTest implements CommandLineRunner {
//  @Autowired
//  private OrderProviderController orderProviderController;
////  @PostConstruct
////  private void init(){
////      System.out.println("看看走不走配置類");
////  }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("走了吗");
//        orderProviderController.getAll();
//        System.out.println("-------------"+orderProviderController.getAll());
//    }
//}
