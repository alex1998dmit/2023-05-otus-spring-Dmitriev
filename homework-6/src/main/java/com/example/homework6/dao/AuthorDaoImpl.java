package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Author createAuthor(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        entityManager.merge(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author author = entityManager.find(Author.class, authorId);
        entityManager.remove(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Long authorId) {
        return entityManager.find(Author.class, authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return entityManager.createQuery("from Author", Author.class).getResultList();
    }
}
