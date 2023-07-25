package org.homework;

import org.assertj.core.groups.Tuple;
import org.homework.dao.QuestionsDao;
import org.homework.dao.QuestionsDaoImpl;
import org.homework.domain.Question;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class QuestionsDaoTest {
    @Test
    public void getQuestionsTest() {
        QuestionsDao questionsDao = new QuestionsDaoImpl("/questions.csv");
        List<Question> questionList = questionsDao.getQuestions();
        assertThat(questionList)
                .hasSize(2)
                .extracting(Question::getQuestion, Question::getRightAnswer)
                .contains(Tuple.tuple("Test1", "Answer1"), Tuple.tuple("Test2", "Answer2"));
    }
}
