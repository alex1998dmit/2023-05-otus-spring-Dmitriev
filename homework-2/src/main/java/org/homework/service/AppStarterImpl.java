package org.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppStarterImpl implements AppStarter {
    private final ExamService examService;

    @Autowired
    public AppStarterImpl(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public void run() {
        examService.exam();
    }
}
