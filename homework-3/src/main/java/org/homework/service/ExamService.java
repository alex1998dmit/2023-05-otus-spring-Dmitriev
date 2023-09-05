package org.homework.service;

public interface ExamService {
    void exam();

    Boolean isExamPassed();

    int getAmountOfRightAnswersGiven();
}
