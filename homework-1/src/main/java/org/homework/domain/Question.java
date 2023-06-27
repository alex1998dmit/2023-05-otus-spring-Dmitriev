package org.homework.domain;

public class Question {
    private String question;

    private String rightAnswer;

    public Question(String question, String rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
