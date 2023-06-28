package org.homework;

import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.service.ExamService;
import org.homework.service.ExamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {

    ExamService examService;

    @Mock
    private QuestionsDao questionsDao;

    @Before
    public void init() throws Exception {
        Mockito.when(questionsDao.getQuestions()).thenReturn(
                List.of(
                        new Question("Test1", "Answer1"),
                        new Question("Test2", "Answer2")
                )
        );
        int answersToPass = 1;
        examService = new ExamServiceImpl(questionsDao, answersToPass);
    }

    @Test
    public void successTest() throws Exception {
        InputStream in = new ByteArrayInputStream("alex\ndmit\nAnswer1\nAnswer2".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);
        examService.setScanner(scanner);
        examService.exam();
        assertEquals(examService.getAmountOfRightAnswersGiven(), 2);
        assertTrue(examService.isExamPassed());
    }
}
