package org.homework.dao;

import org.homework.domain.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions() throws Exception;
}
