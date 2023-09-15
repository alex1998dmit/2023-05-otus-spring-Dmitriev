package com.example.homework6.dao;

import com.example.homework6.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Book createBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        entityManager.merge(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        Book book = entityManager.find(Book.class, bookId);
        entityManager.remove(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long bookId) {
        return entityManager.find(Book.class, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }
}
