package org.homework.service;

import org.homework.domain.Student;

public interface ExamService {
    void exam();

    Boolean isExamPassed();

    int getAmountOfRightAnswersGiven();

    Student fillUserInfo();

    void printExamResults();
}
