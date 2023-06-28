package org.homework.config;

import org.homework.dao.QuestionsDao;
import org.homework.service.ExamService;
import org.homework.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExamServiceConfig {
    @Bean
    public ExamService examService(QuestionsDao questionsDao, @Value("${answersToPass}") int answersToPass) {
        return new ExamServiceImpl(questionsDao, answersToPass);
    }
}
