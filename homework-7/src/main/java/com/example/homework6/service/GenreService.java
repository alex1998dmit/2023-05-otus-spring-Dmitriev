package com.example.homework6.service;

import com.example.homework6.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre createGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenre(Long genreId);

    Genre getById(Long genreId);

    List<Genre> getAll();
}
