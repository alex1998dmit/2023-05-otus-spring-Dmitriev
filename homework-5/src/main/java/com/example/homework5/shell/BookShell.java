package com.example.homework5.shell;

import com.example.homework5.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BookShell {
    private final BookService bookService;

    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "add book", key = {"add_book", "ab"})
    public void addBook() {
        bookService.createBook();
    }

    @ShellMethod(value = "show book", key = {"show_book", "sb"})
    public void showBook() {
        bookService.getAll();
    }

    @ShellMethod(value = "delete book", key = {"delete_book", "db"})
    public void deleteBook() {
        bookService.deleteBook();
    }

    @ShellMethod(value = "update genre", key = {"update_genre", "ug"})
    public void updateBook() {
        bookService.updateBook();
    }

    @ShellMethod(value = "show single book", key = {"show_single_book", "ssb"})
    public void showSingleBook() {
        bookService.getById();
    }
}
