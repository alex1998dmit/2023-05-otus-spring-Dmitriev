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
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    private Genre genre;

    @DBRef
    private Author author;

    @EqualsAndHashCode.Exclude
    @DBRef
    private List<Comment> comments;
}