package com.example.homework6.shell;

import com.example.homework6.domain.Author;
import com.example.homework6.service.AuthorService;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleWriter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.List;

@ShellComponent
public class AuthorShell {
    private final AuthorService authorService;

    private final ConsoleReader consoleReader;

    private final ConsoleWriter consoleWriter;

    public AuthorShell(AuthorService authorService, ConsoleReader consoleReader, ConsoleWriter consoleWriter) {
        this.authorService = authorService;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    @ShellMethod(value = "add author", key = {"add_author", "aa"})
    public void createAuthor() {
        String firstName = consoleReader.read("Enter author firstName: ");
        String lastName = consoleReader.read("Enter author middleName: ");
        String middleName = consoleReader.read("Enter author middleName: ");
        Author author = Author.builder().firstName(firstName).lastName(lastName).middleName(middleName).build();
        authorService.createAuthor(author);
        consoleWriter.write("Author added");
    }

    @ShellMethod(value = "show authors", key = {"show_author", "sa"})
    public void showAuthors() {
        List<Author> authorList = authorService.getAll();
        for (var author : authorList) {
            consoleWriter.writeEntityInfo(author);
        }
    }

    @ShellMethod(value = "delete author", key = {"delete_author", "da"})
    public void deleteAuthor() {
        String authorId = readAuthorId();
        authorService.deleteAuthor(authorId);
        consoleWriter.write("Author deleted.");
    }

    @ShellMethod(value = "update author", key = {"update_author", "ua"})
    public void updateAuthor() {
        String authorId = readAuthorId();
        String firstName = consoleReader.read("Enter author firstName: ");
        String lastName = consoleReader.read("Enter author middleName: ");
        String middleName = consoleReader.read("Enter author middleName: ");
        Author author = Author.builder()
                .id(authorId)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .build();
        authorService.updateAuthor(author);
        consoleWriter.write("Author updated.");
    }

    @ShellMethod(value = "show single author", key = {"show_single_user", "ssa"})
    public void showSingleUser() {
        String authorId = readAuthorId();
        Author author = authorService.getById(authorId);
        consoleWriter.writeEntityInfo(author);
    }

    private String readAuthorId() {
        return consoleReader.read("Enter author id: ");
    }
}