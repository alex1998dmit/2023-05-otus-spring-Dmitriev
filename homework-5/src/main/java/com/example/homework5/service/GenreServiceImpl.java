package com.example.homework5.service;

import com.example.homework5.dao.GenreDao;
import com.example.homework5.domain.Genre;
import com.example.homework5.utils.ConsoleReader;
import com.example.homework5.utils.ConsoleReaderImpl;
import com.example.homework5.utils.ConsoleWriter;
import com.example.homework5.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
                genreId,
                Genre.builder().title(title).build()
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
        Optional<Genre> genre = genreDao.getById(genreId);
        printGenreInfo(genre);
    }

    @Override
    public void getAll() {
        List<Genre> genres = genreDao.getAll();
        for (var genre : genres) {
            printGenreInfo(genre);
        }
    }

    private Long readGenreId() {
        return consoleReader.readLong("Enter genre id: ");
    }

    private void printGenreInfo(Optional<Genre> genre) {
        genre.ifPresent(value ->
                consoleWriter.write(value.getTitle()));
    }

    private void printGenreInfo(Genre genre) {
        consoleWriter.write(genre.getId() + " - " + genre.getTitle());
    }
}
