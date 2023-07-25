package org.homework;

import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.service.ExamService;
import org.homework.service.ExamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.io.*;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {

    @Mock
    private QuestionsDao questionsDao;

    private final PrintStream writer = System.out;

    private final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

    private final String firstName = "Alexander";

    private final String lastName = "Dmitriev";

    private final String rightAnswer1 = "Answer1";

    private final String rightAnswer2 = "Answer2";

    @InjectMocks
    int answersToPass = 1;
    @Before
    public void init() {
        Mockito.when(questionsDao.getQuestions()).thenReturn(
                List.of(
                        new Question("Test1", "Answer1"),
                        new Question("Test2", "Answer2")
                )
        );
    }

    @Test
    public void successTest() throws IOException {
        ExamService examService = new ExamServiceImpl(questionsDao, answersToPass, bufferedReader, writer);
        Mockito.when(bufferedReader.readLine())
                .thenReturn(firstName)
                .thenReturn(lastName)
                .thenReturn(rightAnswer1)
                .thenReturn(rightAnswer2);
        examService.exam();
        assertEquals(examService.getAmountOfRightAnswersGiven(), 2);
        assertTrue(examService.isExamPassed());
    }
}
