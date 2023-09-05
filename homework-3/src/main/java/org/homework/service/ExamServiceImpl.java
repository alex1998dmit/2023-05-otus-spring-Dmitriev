package org.homework.service;

import org.homework.config.ApplicationSettings;
import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private static final String EXAM_WELCOME = "start.welcome";

    private static final String QUESTION_TITLE = "progress.question.text";

    private static final String ANSWER_TITLE = "progress.answer.text";

    private static final String WHAT_STUDENT_FIRST_NAME_TITLE = "start.what_student_first_name";

    private static final String WHAT_STUDENT_LAST_NAME_TITLE = "start.what_student_last_name";

    private static final String RIGHT_ANSWER_TITLE = "progress.answer.right";

    private static final String WRONG_ANSWER_TITLE = "progress.answer.wrong";

    private static final String CONGRATS_TITLE = "progress.final.success";

    private static final String REGRETS_TITLE = "progress.final.failed";

    private final QuestionsDao questionsDao;

    private final BufferedReader in;

    private final PrintStream out;

    private Student student;

    private int rightAnswersGiven = 0;

    private final MessageSource messageSource;

    private final ApplicationSettings applicationSettings;

    public ExamServiceImpl(
            QuestionsDao questionsDao,
            MessageSource messageSource,
            BufferedReader in,
            PrintStream out,
            ApplicationSettings applicationSettings
    ) {
        this.questionsDao = questionsDao;
        this.in = in;
        this.out = out;
        this.messageSource = messageSource;
        this.applicationSettings = applicationSettings;
    }

    @Autowired
    public ExamServiceImpl(
            QuestionsDao questionsDao,
            MessageSource messageSource,
            ApplicationSettings applicationSettings
    ) {
        this.questionsDao = questionsDao;
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
        this.messageSource = messageSource;
        this.applicationSettings = applicationSettings;
    }

    @Override
    public void exam() {
        printHelloInfo();
        List<Question> questionList = questionsDao.getQuestions();
        fillUserInfo();
        answerTheQuestions(questionList);
        printExamResults();
    }

    @Override
    public int getAmountOfRightAnswersGiven() {
        return rightAnswersGiven;
    }

    @Override
    public Boolean isExamPassed() {
        return this.rightAnswersGiven >= this.applicationSettings.getAnswersToPass();
    }

    private String readLine() {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private void printHelloInfo() {
        printMessage(EXAM_WELCOME);
    }

    private void fillUserInfo() {
        printMessage(WHAT_STUDENT_FIRST_NAME_TITLE);
        String firstName = readLine();
        printMessage(WHAT_STUDENT_LAST_NAME_TITLE);
        String lastName = readLine();
        this.student = new Student(firstName, lastName);
    }

    private void printAndAnswerTheQuestion(Question question) {
        printMessage(QUESTION_TITLE, ": " + question.getQuestion());
        printMessage(ANSWER_TITLE, ": ");
        String answer = readLine();
        if (answer.equalsIgnoreCase(question.getRightAnswer())) {
            rightAnswersGiven = rightAnswersGiven + 1;
            printMessage(RIGHT_ANSWER_TITLE);
        } else {
            printMessage(WRONG_ANSWER_TITLE);
        }
    }

    private void printExamResults() {
        out.print(student.getFullName() + " ");
        printMessage(isExamPassed() ? CONGRATS_TITLE : REGRETS_TITLE);
    }

    private void answerTheQuestions(List<Question> questionArray) {
        questionArray.forEach(this::printAndAnswerTheQuestion);
    }

    private String getMessage(String s) {
        return messageSource.getMessage(s, null, applicationSettings.getLocale());
    }

    private void printMessage(String s) {
        out.println(getMessage(s));
    }

    private void printMessage(String messageCode, String restMessage) {
        out.println(getMessage(messageCode) + restMessage);
    }
}
