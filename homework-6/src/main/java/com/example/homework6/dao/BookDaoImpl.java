package com.example.homework6.dao;

import com.example.homework6.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book createBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        entityManager.merge(book);
        return book;
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = entityManager.find(Book.class, bookId);
        entityManager.remove(book);
    }

    @Override
    public Book getById(Long bookId) {
        return entityManager.find(Book.class, bookId);
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }
}
