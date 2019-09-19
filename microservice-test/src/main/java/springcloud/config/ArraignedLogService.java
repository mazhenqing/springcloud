package springcloud.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springcloud.controller.OrderProviderController;

import java.util.Timer;
import java.util.TimerTask;


//@Service
//public class ArraignedLogService implements InitializingBean {
////    private static Timer timer=new Timer();
//    @Autowired
//    private OrderProviderController orderProviderController;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
////        class task extends TimerTask{
////            @Override
////            public void run() {
//                System.out.println("走了吗");
//                orderProviderController.getAll();
//                System.out.println("-------------"+orderProviderController.getAll());
////            }
////        }
////        task tt=new task();
////        timer.schedule(tt,2000);
//    }
//
//}
