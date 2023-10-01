package com.example.homework6.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("middleName")
    private String middleName;

    @EqualsAndHashCode.Exclude
    @DBRef
    private List<Book> books;

    public String getFullName() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
    }
}