package com.example.homework6.service;

import com.example.homework6.dao.GenreDao;
import com.example.homework6.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    @Transactional
    public Genre createGenre(Genre genre) {
        return genreDao.createGenre(genre);
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        return genreDao.updateGenre(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(Long genreId) {
        genreDao.deleteGenre(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(Long genreId) {
        return genreDao.getById(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
