package com.example.homework5.dao;

import com.example.homework5.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreDao {
    void createGenre(Genre genre);

    void updateGenre(Long genreId, Genre genre);

    void deleteGenre(Long genreId);

    Optional<Genre> getById(Long bookId);

    List<Genre> getAll();
}