package org.homework.config;

import org.homework.dao.QuestionsDao;
import org.homework.dao.QuestionsDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionDaoConfig {
    @Bean
    public QuestionsDao questionsDao(@Value("${filePath}") String filePath) {
        return new QuestionsDaoImpl(filePath);
    }
}
