package org.homework.service;

import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;

import java.io.IOException;
import java.util.List;

public class ExamServiceImpl implements ExamService {
    private static final String QUESTION_TITLE = "Question: ";

    private static final String ANSWER_TITLE = "Answer: ";

    private final QuestionsDao questionsDao;

    public ExamServiceImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    public void printQuestionAndAnswer(Question question) {
        System.out.println(QUESTION_TITLE + question.getQuestion());
        System.out.println(ANSWER_TITLE + question.getRightAnswer());
    }

    @Override
    public void exam() throws IOException {
        List<Question> questionList = questionsDao.getQuestions();
        questionList.forEach(this::printQuestionAndAnswer);
    }
}
