package org.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Question {
    private final String question;

    private final String rightAnswer;
}
