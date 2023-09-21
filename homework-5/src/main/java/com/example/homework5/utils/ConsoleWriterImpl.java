package com.example.homework5.utils;

import com.example.homework5.domain.Author;
import com.example.homework5.domain.Book;
import com.example.homework5.domain.Genre;
import java.io.PrintStream;

public class ConsoleWriterImpl implements ConsoleWriter {
    private final PrintStream out = System.out;

    @Override
    public void write(String message) {
        out.println(message);
    }

    @Override
    public void writeEntityInfo(Book book) {
        write("| " + book.getId() + " - " + book.getTitle() + " - " + book.getAuthor().getFullName() + " - " + book.getGenre().getTitle() + " |");
    }

    @Override
    public void writeEntityInfo(Genre genre) {
        write("| " + genre.getId() + " - " + genre.getTitle() + " |");
    }

    @Override
    public void writeEntityInfo(Author author) {
        write(
                "| " + author.getId() + " - " + author.getFirstName() + " - "
                        + author.getLastName() + " - " + author.getMiddleName() + " |"
        );
    }
}
