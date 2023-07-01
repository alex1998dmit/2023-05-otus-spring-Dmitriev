package org.example.homework3.dao;



import org.example.homework3.domain.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions() throws Exception;
}
