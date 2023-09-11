package com.example.homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Book {
    private Long id;

    private String title;

    private Long authorId;

    private Long genreId;
}
