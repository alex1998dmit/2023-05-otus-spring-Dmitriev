package com.example.homework6.utils;

import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Comment;
import com.example.homework6.domain.Genre;
import org.springframework.stereotype.Component;
import java.io.PrintStream;

@Component
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

    @Override
    public void writeEntityInfo(Comment comment) {
        write(
                "| " + comment.getId() + " - " + comment.getBook().getTitle() + " - "
                        + comment.getText() + " |"
        );
    }
}
