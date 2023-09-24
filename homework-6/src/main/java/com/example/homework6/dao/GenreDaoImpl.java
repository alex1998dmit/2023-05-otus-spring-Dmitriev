package com.example.homework6.dao;

import com.example.homework6.domain.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre createGenre(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Genre updateGenre(Genre genre) {
        entityManager.merge(genre);
        return genre;
    }

    @Override
    public void deleteGenre(Long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        entityManager.remove(genre);
    }

    @Override
    public Genre getById(Long genreId) {
        return entityManager.find(Genre.class, genreId);
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("from Genre ", Genre.class).getResultList();
    }
}
