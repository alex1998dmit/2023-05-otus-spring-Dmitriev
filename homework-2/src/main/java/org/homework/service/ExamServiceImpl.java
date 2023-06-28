package org.homework.service;

import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.domain.Student;

import java.util.List;
import java.util.Scanner;

public class ExamServiceImpl implements ExamService {
    private static final String QUESTION_TITLE = "Question: ";

    private static final String ANSWER_TITLE = "Answer: ";

    private static final String WHAT_STUDENT_FIRST_NAME_TITLE = "Your firstname: ";

    private static final String WHAT_STUDENT_LAST_NAME_TITLE = "Your lastname: ";

    private static final String RIGHT_ANSWER_TITLE = "Right answer! ";

    private static final String WRONG_ANSWER_TITLE = "Wrong answer! ";

    private static final String CONGRATS_TITLE = "passed exam! ";

    private static final String REGRETS_TITLE = "failed exam, try again next time";

    private final QuestionsDao questionsDao;

    private Scanner scanner = new Scanner(System.in);

    private Student student;

    private final int answersToPass;

    private int rightAnswersGiven = 0;

    public ExamServiceImpl(QuestionsDao questionsDao, int answersToPass) {
        this.questionsDao = questionsDao;
        this.answersToPass = answersToPass;
    }

    private void fillUserInfo() {
        System.out.println(WHAT_STUDENT_FIRST_NAME_TITLE);
        String firstName = scanner.nextLine();
        System.out.println(WHAT_STUDENT_LAST_NAME_TITLE);
        String lastName = scanner.nextLine();
        this.student = new Student(firstName, lastName);
    }

    private void printAndAnswerTheQuestion(Question question) {
        System.out.println(QUESTION_TITLE + question.getQuestion());
        System.out.println(ANSWER_TITLE);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(question.getRightAnswer())) {
            rightAnswersGiven = rightAnswersGiven + 1;
            System.out.println(RIGHT_ANSWER_TITLE);
        } else {
            System.out.println(WRONG_ANSWER_TITLE);
        }
    }

    public int getAmountOfRightAnswersGiven() {
        return rightAnswersGiven;
    }

    public Boolean isExamPassed() {
        return this.rightAnswersGiven >= this.answersToPass;
    }

    private void printExamResults() {
        System.out.print(student.getFullName() + " ");
        System.out.print(isExamPassed() ? CONGRATS_TITLE : REGRETS_TITLE);
    }

    private void answerTheQuestions(List<Question> questionArray) {
        questionArray.forEach(this::printAndAnswerTheQuestion);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void exam() throws Exception {
        List<Question> questionList = questionsDao.getQuestions();
        fillUserInfo();
        answerTheQuestions(questionList);
        printExamResults();
    }
}
