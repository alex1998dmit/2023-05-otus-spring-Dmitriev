package org.homework.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.homework.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    private final String path;

    public QuestionsDaoImpl(String path) {
        this.path = path;
    }

    private List<CSVRecord> readQuestionsFromCSV() throws Exception {
        InputStream is = this.getClass().getResourceAsStream(path);
        assert is != null;
        try(
                InputStreamReader reader = new InputStreamReader(is);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create().setDelimiter(';').build())
        ) {
            return csvParser.getRecords();
        }
    }

    @Override
    public List<Question> getQuestions() throws Exception {
        List<Question> questionList = new ArrayList<>();
        this.readQuestionsFromCSV().forEach(record -> questionList.add(new Question(record.get(0), record.get(1))));
        return questionList;
    }
}
