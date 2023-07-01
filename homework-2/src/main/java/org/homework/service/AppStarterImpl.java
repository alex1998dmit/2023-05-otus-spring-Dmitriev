package org.homework.service;

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
