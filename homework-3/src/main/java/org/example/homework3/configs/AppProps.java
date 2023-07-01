package org.example.homework3.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private final String filePath;

    private final Integer answersToPass;


    @ConstructorBinding
    public AppProps(String filePath, Integer answersToPass) {
        this.filePath = filePath;
        this.answersToPass = answersToPass;
    }

    public int getAnswersToPass() {
        return answersToPass;
    }

    public String getFilePath() {
        return filePath;
    }
}
