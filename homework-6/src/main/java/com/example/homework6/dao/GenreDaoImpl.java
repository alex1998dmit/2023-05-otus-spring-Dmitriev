package com.example.homework6.dao;

import com.example.homework6.domain.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Genre createGenre(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    @Transactional
    public void updateGenre(Genre genre) {
        entityManager.merge(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(Long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        entityManager.remove(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(Long genreId) {
        return entityManager.find(Genre.class, genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return entityManager.createQuery("from Genre ", Genre.class).getResultList();
    }
}
