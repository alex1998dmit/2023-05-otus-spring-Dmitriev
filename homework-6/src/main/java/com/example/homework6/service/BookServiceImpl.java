package com.example.homework6.service;

import com.example.homework6.dao.BookDao;
import com.example.homework6.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookDao.createBook(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long bookId) {
        return bookDao.getById(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
