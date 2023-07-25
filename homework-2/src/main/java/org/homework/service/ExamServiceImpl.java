package org.homework.service;

import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

@Service
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

    private final BufferedReader in;

    private final PrintStream out;

    private Student student;

    private final int answersToPass;

    private int rightAnswersGiven = 0;

    public ExamServiceImpl(
            QuestionsDao questionsDao,
            @Value("${answersToPass}") int answersToPass,
            BufferedReader in,
            PrintStream out
    ) {
        this.questionsDao = questionsDao;
        this.answersToPass = answersToPass;
        this.in = in;
        this.out = out;
    }

    @Autowired
    public ExamServiceImpl(QuestionsDao questionsDao, @Value("${answersToPass}") int answersToPass) {
        this.questionsDao = questionsDao;
        this.answersToPass = answersToPass;
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
    }

    @Override
    public void exam() {
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
        return this.rightAnswersGiven >= this.answersToPass;
    }

    private String readLine() {
        String line = null;
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

    private void fillUserInfo() {
        out.println(WHAT_STUDENT_FIRST_NAME_TITLE);
        String firstName = readLine();
        out.println(WHAT_STUDENT_LAST_NAME_TITLE);
        String lastName = readLine();
        this.student = new Student(firstName, lastName);
    }

    private void printAndAnswerTheQuestion(Question question) {
        out.println(QUESTION_TITLE + question.getQuestion());
        out.println(ANSWER_TITLE);
        String answer = readLine();
        if (answer.equalsIgnoreCase(question.getRightAnswer())) {
            rightAnswersGiven = rightAnswersGiven + 1;
            out.println(RIGHT_ANSWER_TITLE);
        } else {
            out.println(WRONG_ANSWER_TITLE);
        }
    }

    private void printExamResults() {
        out.print(student.getFullName() + " ");
        out.print(isExamPassed() ? CONGRATS_TITLE : REGRETS_TITLE);
    }

    private void answerTheQuestions(List<Question> questionArray) {
        questionArray.forEach(this::printAndAnswerTheQuestion);
    }
}
