package com.example.homework6.service;

import com.example.homework6.domain.Book;
import java.util.List;

public interface BookService {
    void createBook();

    void updateBook();

    void deleteBook();

    Book getById();

    List<Book> getAll();
}
