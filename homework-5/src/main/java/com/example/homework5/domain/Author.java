package com.example.homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Author {
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    public String getFullName() {
        return this.lastName + " " + this.firstName + " " + this.middleName;
    }
}
