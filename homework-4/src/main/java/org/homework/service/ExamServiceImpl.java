package org.homework.service;

import org.homework.config.ApplicationSettings;
import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.domain.Student;
import org.homework.utils.ConsoleReader;
import org.homework.utils.ConsoleReaderImpl;
import org.homework.utils.ConsoleWriter;
import org.homework.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
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

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private int rightAnswersGiven = 0;

    private final MessageSource messageSource;

    private final ApplicationSettings applicationSettings;

    public ExamServiceImpl(
            QuestionsDao questionsDao,
            MessageSource messageSource,
            ConsoleReader consoleReader,
            ApplicationSettings applicationSettings
    ) {
        this.questionsDao = questionsDao;
        this.consoleReader = consoleReader;
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
        this.messageSource = messageSource;
        this.applicationSettings = applicationSettings;
    }

    @Override
    public void exam() {
        printHelloInfo();
        List<Question> questionList = questionsDao.getQuestions();
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

    public void printExamResults() {
        printMessage(isExamPassed() ? CONGRATS_TITLE : REGRETS_TITLE);
    }

    public Student fillUserInfo() {
        printMessage(WHAT_STUDENT_FIRST_NAME_TITLE);
        String firstName = consoleReader.read();
        printMessage(WHAT_STUDENT_LAST_NAME_TITLE);
        String lastName = consoleReader.read();
        return new Student(firstName, lastName);
    }

    private void printHelloInfo() {
        printMessage(EXAM_WELCOME);
    }

    private void printAndAnswerTheQuestion(Question question) {
        printMessage(QUESTION_TITLE, ": " + question.getQuestion());
        printMessage(ANSWER_TITLE, ": ");
        String answer = consoleReader.read();
        if (answer.equalsIgnoreCase(question.getRightAnswer())) {
            rightAnswersGiven = rightAnswersGiven + 1;
            printMessage(RIGHT_ANSWER_TITLE);
        } else {
            printMessage(WRONG_ANSWER_TITLE);
        }
    }

    private void answerTheQuestions(List<Question> questionArray) {
        questionArray.forEach(this::printAndAnswerTheQuestion);
    }

    private String getMessage(String s) {
        return messageSource.getMessage(s, null, applicationSettings.getLocale());
    }

    private void printMessage(String s) {
        consoleWriter.write(getMessage(s));
    }

    private void printMessage(String messageCode, String restMessage) {
        consoleWriter.write(getMessage(messageCode) + restMessage);
    }
}
