package bts.lab_lune.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContext {
    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}