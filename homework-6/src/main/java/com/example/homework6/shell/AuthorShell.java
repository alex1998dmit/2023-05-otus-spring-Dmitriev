package com.example.homework6.shell;

import com.example.homework6.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AuthorShell {
    private final AuthorService authorService;

    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "add author", key = {"add_author", "aa"})
    public void createAuthor() {
        authorService.createAuthor();
    }

    @ShellMethod(value = "show authors", key = {"show_author", "sa"})
    public void showAuthors() {
        authorService.getAll();
    }

    @ShellMethod(value = "delete author", key = {"delete_author", "da"})
    public void deleteAuthor() {
        authorService.deleteAuthor();
    }

    @ShellMethod(value = "update author", key = {"update_author", "ua"})
    public void updateAuthor() {
        authorService.updateAuthor();
    }

    @ShellMethod(value = "show single author", key = {"show_single_user", "ssa"})
    public void showSingleUser() {
        authorService.getById();
    }
}
