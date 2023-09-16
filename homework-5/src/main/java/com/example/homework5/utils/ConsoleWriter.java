package com.example.homework5.utils;

import com.example.homework5.domain.Author;
import com.example.homework5.domain.Book;
import com.example.homework5.domain.Genre;

public interface ConsoleWriter {
    void write(String message);

    void writeEntityInfo(Book book);

    void writeEntityInfo(Genre genre);

    void writeEntityInfo(Author author);
}
