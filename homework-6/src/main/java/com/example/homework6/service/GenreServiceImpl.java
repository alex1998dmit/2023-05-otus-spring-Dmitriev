package com.example.homework6.service;

import com.example.homework6.dao.GenreDao;
import com.example.homework6.domain.Genre;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleReaderImpl;
import com.example.homework6.utils.ConsoleWriter;
import com.example.homework6.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public GenreServiceImpl(GenreDao genreDao, ConsoleReader consoleReader) {
        this.genreDao = genreDao;
        this.consoleReader = consoleReader;
    }

    @Override
    public void createGenre() {
        String title = consoleReader.read("Enter title of genre: ");
        genreDao.createGenre(
                Genre.builder().title(title).build()
        );
        consoleWriter.write("Genre added.");
    }

    @Override
    public void updateGenre() {
        Long genreId = readGenreId();
        String title = consoleReader.read("Enter title of genre: ");

        genreDao.updateGenre(
                Genre.builder().id(genreId).title(title).build()
        );
        consoleWriter.write("Genre updated.");
    }

    @Override
    public void deleteGenre() {
        Long genreId = readGenreId();
        genreDao.deleteGenre(genreId);
        consoleWriter.write("Genre deleted.");
    }

    @Override
    public void getById() {
        Long genreId = readGenreId();
        Genre genre = genreDao.getById(genreId);
        consoleWriter.writeEntityInfo(genre);
    }

    @Override
    public void getAll() {
        List<Genre> genres = genreDao.getAll();
        for (var genre : genres) {
            consoleWriter.writeEntityInfo(genre);
        }
    }

    private Long readGenreId() {
        return consoleReader.readLong("Enter genre id: ");
    }
}
