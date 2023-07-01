package org.example.homework3.commandlinerunners;

import org.example.homework3.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PreparationDev implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationDev.class);

    private final ExamService examService;

    public PreparationDev(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public void run(String... args) {
        var applArgs = Arrays.toString(args);
        logger.info("DEV mode!!! Что-то настравиваем и подготавливаем, параметры: {} ", applArgs);

//        examService.exam();
        logger.info("reply:{}", "dsdsad");
    }
}
