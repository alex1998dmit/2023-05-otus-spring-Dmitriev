package org.homework.service;

import org.springframework.stereotype.Service;

@Service
public class AppStarterImpl implements AppStarter {
    private final ExamService examService;

    public AppStarterImpl(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public void run() {
        examService.exam();
    }
}
