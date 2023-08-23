package org.homework;

import org.homework.config.ApplicationSettings;
import org.homework.service.AppStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationSettings.class)
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        AppStarter appStarter = context.getBean(AppStarter.class);
        appStarter.run();
    }
}
