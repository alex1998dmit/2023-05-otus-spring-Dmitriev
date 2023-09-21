package com.example.homework5.service;

import com.example.homework5.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void createBook();

    void updateBook();

    void deleteBook();

    Optional<Book> getById();

    List<Book> getAll();
}
