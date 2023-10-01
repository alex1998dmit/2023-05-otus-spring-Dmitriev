package com.example.homework6.service;

import com.example.homework6.domain.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);

    Author updateAuthor(Author author);

    Author getById(String authorId);

    void deleteAuthor(String authorId);

    List<Author> getAll();
}
