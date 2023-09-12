package org.homework;

import org.homework.service.AppStarter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AppStarter appStarter = context.getBean(AppStarter.class);
        appStarter.run();
    }
}
