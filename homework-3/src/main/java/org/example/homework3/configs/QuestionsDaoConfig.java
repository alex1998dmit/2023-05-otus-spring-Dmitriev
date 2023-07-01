package org.example.homework3.configs;

import org.example.homework3.dao.QuestionsDao;
import org.example.homework3.dao.QuestionsDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class QuestionsDaoConfig {
    @Bean
    public QuestionsDao questionsDao(@Value("${application.filePath}") String filePath) {
        return new QuestionsDaoImpl(filePath);
    }
}
