package org.example.homework3.configs;

import org.example.homework3.service.AppStarter;
import org.example.homework3.service.AppStarterImpl;
import org.example.homework3.service.ExamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarterConfig {
    @Bean
    AppStarter appStarter(ExamService examService) {
        return new AppStarterImpl(examService);
    }
}
