package org.homework.service;

import java.io.IOException;

public class AppStarterImpl implements AppStarter {
    private final ExamService examService;

    public AppStarterImpl(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public void run() throws IOException {
        examService.exam();
    }
}
