package org.example.homework3.dao;


import org.example.homework3.domain.Question;

import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    private final String path;

    public QuestionsDaoImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Question> getQuestions() throws Exception {
        return null;
    }
}
