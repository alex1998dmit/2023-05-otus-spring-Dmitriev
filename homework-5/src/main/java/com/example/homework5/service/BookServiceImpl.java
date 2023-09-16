package com.example.homework5.service;

import com.example.homework5.dao.BookDao;
import com.example.homework5.domain.Book;
import com.example.homework5.utils.ConsoleReader;
import com.example.homework5.utils.ConsoleReaderImpl;
import com.example.homework5.utils.ConsoleWriter;
import com.example.homework5.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookServiceImpl(BookDao bookDao, ConsoleReader consoleReader) {
        this.bookDao = bookDao;
        this.consoleReader = consoleReader;
    }

    @Override
    public void createBook() {
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Long genreId = consoleReader.readLong("Enter genre id: ");
        bookDao.createBook(
                Book.builder().title(title).authorId(authorId).genreId(genreId).build()
        );
        consoleWriter.write("Book added.");
    }

    @Override
    public void updateBook() {
        Long bookId = readBookId();
        String title = consoleReader.read("Enter book title: ");
        Long authorId = consoleReader.readLong("Enter author id: ");
        Long genreId = consoleReader.readLong("Enter genre id: ");
        bookDao.updateBook(
                bookId,
                Book.builder().title(title).authorId(authorId).genreId(genreId).build()
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
    public Optional<Book> getById() {
        Long bookId = readBookId();
        Optional<Book> book = bookDao.getById(bookId);
        book.ifPresent(consoleWriter::writeEntityInfo);
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
