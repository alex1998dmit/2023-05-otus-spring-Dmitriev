package com.example.homework6.utils;

import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Comment;
import com.example.homework6.domain.Genre;

public interface ConsoleWriter {
    void write(String message);

    void writeEntityInfo(Book book);

    void writeEntityInfo(Genre genre);

    void writeEntityInfo(Author author);

    void writeEntityInfo(Comment comment);
}
