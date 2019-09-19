package springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import springcloud.controller.OrderProviderController;

import java.util.Timer;
import java.util.TimerTask;
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OrderProviderController orderProviderController;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            createSitemap();
        }
    }

    private void createSitemap() {
        Timer timer = new Timer("createSitemap", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("--->Create sitemap...");
                orderProviderController.getAll();
                System.out.println("--->Success create sitemap...");
                System.out.println("-------------"+orderProviderController.getAll());
            }
        }, 1 );
    }
}
