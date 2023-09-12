package org.homework.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.homework.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    private final String path;

    public QuestionsDaoImpl(String path) {
        this.path = path;
    }

    public List<Question> getQuestions() throws IOException {
        InputStream is = this.getClass().getResourceAsStream(path);
        List<Question> questionList = new ArrayList<>();
        assert is != null;
        try(
                InputStreamReader reader = new InputStreamReader(is);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create().setDelimiter(';').build())
        ) {
            csvParser.getRecords().forEach(record -> questionList.add(new Question(record.get(0), record.get(1))));
        }
        return questionList;
    }
}
