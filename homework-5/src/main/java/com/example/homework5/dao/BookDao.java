package com.example.homework5.dao;

import com.example.homework5.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void createBook(Book book);

    void updateBook(Long bookId, Book book);

    void deleteBook(Long bookId);

    Optional<Book> getById(Long bookId);

    List<Book> getAll();
}
