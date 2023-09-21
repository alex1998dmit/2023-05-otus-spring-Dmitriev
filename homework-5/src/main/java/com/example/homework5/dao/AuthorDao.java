package com.example.homework5.dao;

import com.example.homework5.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void createAuthor(Author author);

    void updateAuthor(Long authorId, Author author);

    void deleteAuthor(Long authorId);

    Optional<Author> getById(Long authorId);

    List<Author> getAll();
}
