package org.homework.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Student {
    private final String firstName;

    private final String lastName;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
