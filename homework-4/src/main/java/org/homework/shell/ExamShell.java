package org.homework.shell;

import org.homework.config.ApplicationSettings;
import org.homework.domain.Student;
import org.homework.service.ExamService;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ExamShell {
    private final ExamService examService;
    private final MessageSource messageSource;
    private final ApplicationSettings applicationSettings;
    private static final String NOT_LOGGED_IN = "login.not_logged_in";

    private Student student;

    public ExamShell(ExamService examService, MessageSource messageSource, ApplicationSettings applicationSettings) {
        this.examService = examService;
        this.messageSource = messageSource;
        this.applicationSettings = applicationSettings;
    }

    @ShellMethod(value = "Login", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Guest") String userName) {
        this.student = examService.fillUserInfo();
        return String.format("Welcome: %s", student.getFullName());
    }

    @ShellMethod(value = "Start exam", key = {"s", "str", "start", "st", "exam"})
    @ShellMethodAvailability(value = "isLoggedIn")
    public void runExam() {
        examService.exam();
    }

    @ShellMethod(value = "Start exam", key = {"r", "result", "res"})
    public void getResult() {
        examService.printExamResults();
    }
    public Availability isLoggedIn() {
        return this.student != null ?
                Availability.available() :
                Availability.unavailable(messageSource.getMessage(NOT_LOGGED_IN, null, applicationSettings.getLocale()));
    }
}
