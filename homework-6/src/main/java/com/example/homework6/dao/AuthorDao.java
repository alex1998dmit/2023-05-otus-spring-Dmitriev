package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import java.util.List;

public interface AuthorDao {
    Author createAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthor(Long authorId);

    Author getById(Long authorId);

    List<Author> getAll();
}
