package com.example.homework6.dao;

import com.example.homework6.domain.Genre;
import java.util.List;

public interface GenreDao {
    Genre createGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenre(Long genreId);

    Genre getById(Long bookId);

    List<Genre> getAll();
}