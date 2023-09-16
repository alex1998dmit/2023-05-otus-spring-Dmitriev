package com.example.homework6.service;

import com.example.homework6.dao.AuthorDao;
import com.example.homework6.dao.BookDao;
import com.example.homework6.dao.GenreDao;
import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Genre;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleReaderImpl;
import com.example.homework6.utils.ConsoleWriter;
import com.example.homework6.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, ConsoleReader consoleReader) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.consoleReader = consoleReader;
    }

    @Override
    public void createBook() {
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Long genreId = consoleReader.readLong("Enter genre id: ");

        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);


        bookDao.createBook(
                Book.builder()
                        .title(title)
                        .author(author)
                        .genre(genre)
                        .build()
        );
        consoleWriter.write("Book added.");
    }

    @Override
    public void updateBook() {
        Long bookId = readBookId();
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Long genreId = consoleReader.readLong("Enter genre id: ");

        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);

        bookDao.updateBook(
                Book.builder()
                        .id(bookId)
                        .title(title)
                        .author(author)
                        .genre(genre)
                        .build()
        );
        consoleWriter.write("Book updated.");
    }

    @Override
    public void deleteBook() {
        Long bookId = readBookId();
        bookDao.deleteBook(bookId);
        consoleWriter.write("Book deleted.");
    }

    @Override
    public Book getById() {
        Long bookId = readBookId();
        Book book = bookDao.getById(bookId);
        consoleWriter.writeEntityInfo(book);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = bookDao.getAll();
        for (var book : books) {
            consoleWriter.writeEntityInfo(book);
        }
        return books;
    }

    private Long readBookId() {
        return consoleReader.readLong("Enter book id: ");
    }
}
