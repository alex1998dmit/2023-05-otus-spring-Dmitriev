package org.homework.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Setter;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSettings {
    private Locale locale;

    private String filePath;

    private int answersToPass;
}
