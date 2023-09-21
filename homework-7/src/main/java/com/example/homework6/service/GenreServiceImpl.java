package com.example.homework6.service;

import com.example.homework6.dao.GenreDao;
import com.example.homework6.domain.Genre;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreDao.save(genre);

    }

    @Override
    public Genre updateGenre(Genre genre) {
        checkGenreExists(genre.getId());
        return genreDao.save(genre);
    }

    @Override
    public void deleteGenre(Long genreId) {
        Genre genre = getById(genreId);
        genreDao.delete(genre);

    }

    @Override
    public Genre getById(Long genreId) {
        Optional<Genre> genre = genreDao.findById(genreId);
        if (genre.isEmpty()) throw new RuntimeException("Not found genre");
        return genre.get();
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.findAll();
    }

    public void checkGenreExists(Long genreId) {
        boolean isExists = genreDao.existsById(genreId);
        if (!isExists) throw new RuntimeException("No genre found");
    }
}
