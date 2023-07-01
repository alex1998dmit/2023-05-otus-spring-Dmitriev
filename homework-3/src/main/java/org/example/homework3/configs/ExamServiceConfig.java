package org.example.homework3.configs;

import org.example.homework3.dao.QuestionsDao;
import org.example.homework3.service.ExamService;
import org.example.homework3.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class ExamServiceConfig {
    @Bean
    public ExamService examService(
            QuestionsDao questionsDao,
            @Value("${application.answersToPass}") int answersToPass
    ) {
        return new ExamServiceImpl(questionsDao, answersToPass);
    }
}
