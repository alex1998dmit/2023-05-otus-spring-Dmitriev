package com.example.homework6.shell;

import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Genre;
import com.example.homework6.service.AuthorService;
import com.example.homework6.service.BookService;
import com.example.homework6.service.GenreService;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleWriter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.List;

@ShellComponent
public class BookShell {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final ConsoleReader consoleReader;

    private final ConsoleWriter consoleWriter;

    public BookShell(
            BookService bookService,
            ConsoleWriter consoleWriter,
            ConsoleReader consoleReader,
            AuthorService authorService,
            GenreService genreService
    ) {
        this.bookService = bookService;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "add book", key = {"add_book", "ab"})
    public void addBook() {
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Author author = authorService.getById(authorId);
        Long genreId = consoleReader.readLong("Enter genre id: ");
        Genre genre = genreService.getById(genreId);
        Book book = Book.builder()
                .title(title)
                .author(author)
                .genre(genre)
                .build();
        bookService.createBook(book);
        consoleWriter.write("Book added.");
    }

    @ShellMethod(value = "show book", key = {"show_book", "sb"})
    public void showBook() {
        List<Book> books = bookService.getAll();
        for (var book : books) {
            consoleWriter.writeEntityInfo(book);
        }
    }

    @ShellMethod(value = "delete book", key = {"delete_book", "db"})
    public void deleteBook() {
        Long bookId = readBookId();
        bookService.deleteBook(bookId);
        consoleWriter.write("Book deleted.");
    }

    @ShellMethod(value = "update genre", key = {"update_genre", "ug"})
    public void updateBook() {
        Long bookId =  readBookId();
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Author author = authorService.getById(authorId);
        Long genreId = consoleReader.readLong("Enter genre id: ");
        Genre genre = genreService.getById(genreId);
        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .author(author)
                .genre(genre)
                .build();
        bookService.updateBook(book);
        consoleWriter.write("Book updated.");
    }

    @ShellMethod(value = "show single book", key = {"show_single_book", "ssb"})
    public void showSingleBook() {
        Long bookId = readBookId();
        Book book = bookService.getById(bookId);
        consoleWriter.writeEntityInfo(book);
    }

    private Long readBookId() {
        return consoleReader.readLong("Enter book id: ");
    }
}
