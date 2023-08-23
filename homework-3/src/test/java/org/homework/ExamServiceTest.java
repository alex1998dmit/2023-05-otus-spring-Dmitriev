package org.homework;

import org.homework.config.ApplicationSettings;
import org.homework.dao.QuestionsDao;
import org.homework.domain.Question;
import org.homework.service.ExamService;
import org.homework.service.ExamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class ExamServiceTest {
    @Mock
    QuestionsDao questionsDao;
    @Mock
    ApplicationSettings applicationSettings;
    @Mock
    MessageSource messageSource;
    @Mock
    BufferedReader bufferedReader;
    final PrintStream writer = System.out;

    public ExamServiceTest() {
    }

    @Before
    public void init() {
        Mockito.when(questionsDao.getQuestions()).thenReturn(
                List.of(
                        new Question("Test1", "Answer1"),
                        new Question("Test2", "Answer2")
                )
        );

        Mockito.when(applicationSettings.getLocale()).thenReturn(Locale.getDefault());
        Mockito.when(applicationSettings.getAnswersToPass()).thenReturn(2);

        Mockito.when(messageSource.getMessage("start.welcome", null, Locale.getDefault())).thenReturn("Hello! Welcome to exam");
        Mockito.when(messageSource.getMessage("progress.question.text", null, Locale.getDefault())).thenReturn("Question");
        Mockito.when(messageSource.getMessage("progress.answer.text", null, Locale.getDefault())).thenReturn("Answer");
        Mockito.when(messageSource.getMessage("start.what_student_first_name", null, Locale.getDefault())).thenReturn("Your firstname");
        Mockito.when(messageSource.getMessage("progress.answer.right", null, Locale.getDefault())).thenReturn("Right answer!");
        Mockito.when(messageSource.getMessage("progress.answer.wrong", null, Locale.getDefault())).thenReturn("Wrong answer!");
        Mockito.when(messageSource.getMessage("progress.final.success", null, Locale.getDefault())).thenReturn("passed exam!");
        Mockito.when(messageSource.getMessage("progress.final.failed", null, Locale.getDefault())).thenReturn("failed exam, try again next time");
    }

    @Test
    public void successTest() throws IOException {
        ExamService examService = Mockito.spy(new ExamServiceImpl(
                questionsDao,
                messageSource,
                bufferedReader,
                writer,
                applicationSettings
        ));
        String firstName = "Alexander";
        String lastName = "Dmitriev";
        String rightAnswer1 = "Answer1";
        String rightAnswer2 = "Answer2";

        Mockito.when(bufferedReader.readLine())
                .thenReturn(firstName)
                .thenReturn(lastName)
                .thenReturn(rightAnswer1)
                .thenReturn(rightAnswer2);

        examService.exam();

        assertEquals(2, examService.getAmountOfRightAnswersGiven());
        assertTrue(examService.isExamPassed());
    }

    @Test
    public void failedTest() throws IOException {
        ExamService examService = Mockito.spy(new ExamServiceImpl(
                questionsDao,
                messageSource,
                bufferedReader,
                writer,
                applicationSettings
        ));
        String firstName = "Alexander";
        String lastName = "Dmitriev";
        String wrongAnswer1 = "AnswerWrong1";
        String wrongAnswer2 = "AnswerWrong2";

        Mockito.when(bufferedReader.readLine())
                .thenReturn(firstName)
                .thenReturn(lastName)
                .thenReturn(wrongAnswer1)
                .thenReturn(wrongAnswer2);

        examService.exam();

        assertEquals(0, examService.getAmountOfRightAnswersGiven());
        assertFalse(examService.isExamPassed());
    }
}
