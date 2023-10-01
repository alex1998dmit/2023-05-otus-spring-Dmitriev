package com.example.homework6.service;

import com.example.homework6.dao.BookDao;
import com.example.homework6.domain.Book;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        checkBookExists(book.getId());
        return bookDao.save(book);
    }

    @Override
    public void deleteBook(String bookId) {
        Book book = getById(bookId);
        bookDao.delete(book);
    }

    @Override
    public Book getById(String bookId) {
        Optional<Book> book = bookDao.findById(bookId);
        if (book.isEmpty()) throw new RuntimeException("Not book found");
        return book.get();
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public void checkBookExists(String bookId) {
        boolean isExists = bookDao.existsById(bookId);
        if (isExists) return;
        throw new RuntimeException("No book found");
    }
}
