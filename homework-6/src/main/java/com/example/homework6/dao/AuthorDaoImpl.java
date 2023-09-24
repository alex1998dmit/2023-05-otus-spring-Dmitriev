package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author createAuthor(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        return entityManager.merge(author);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Author author = entityManager.find(Author.class, authorId);
        entityManager.remove(author);
    }

    @Override
    public Author getById(Long authorId) {
        return entityManager.find(Author.class, authorId);
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("from Author", Author.class).getResultList();
    }
}
