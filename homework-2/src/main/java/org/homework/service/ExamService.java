package org.homework.service;

import java.util.Scanner;

public interface ExamService {
    void exam();

    void setScanner(Scanner scanner);

    Boolean isExamPassed();

    int getAmountOfRightAnswersGiven();
}
