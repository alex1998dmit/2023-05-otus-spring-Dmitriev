package org.homework.config;

import org.homework.service.AppStarter;
import org.homework.service.AppStarterImpl;
import org.homework.service.ExamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarterConfig {
    @Bean
    AppStarter appStarter(ExamService examService) {
        return new AppStarterImpl(examService);
    }
}
