package com.example.homework6.service;

import com.example.homework6.domain.Book;
import java.util.List;

public interface BookService {
    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(String bookId);

    Book getById(String bookId);

    List<Book> getAll();
}
