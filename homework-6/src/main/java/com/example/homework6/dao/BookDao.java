package com.example.homework6.dao;

import com.example.homework6.domain.Book;

import java.util.List;

public interface BookDao {
    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long bookId);

    Book getById(Long bookId);

    List<Book> getAll();
}
