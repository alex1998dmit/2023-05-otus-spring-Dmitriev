package org.homework.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.homework.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsDaoImpl implements QuestionsDao {
    private final String path;

    public QuestionsDaoImpl(@Value("${application.filePath}_${application.locale}.csv") String filePath) {
        this.path = filePath;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        this.readQuestionsFromCSV().forEach(
                record -> questionList.add(
                        new Question(record.get(0), record.get(1))
                )
        );
        return questionList;
    }

    private List<CSVRecord> readQuestionsFromCSV() {
        InputStream is = this.getClass().getResourceAsStream(path);
        assert is != null;
        try (
                InputStreamReader reader = new InputStreamReader(is);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create().setDelimiter(';').build())
        ) {
            return csvParser.getRecords();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
