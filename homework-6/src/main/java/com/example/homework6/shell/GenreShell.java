package com.example.homework6.shell;

import com.example.homework6.domain.Genre;
import com.example.homework6.service.GenreService;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleWriter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.List;

@ShellComponent
public class GenreShell {
    private final GenreService genreService;

    private final ConsoleReader consoleReader;

    private final ConsoleWriter consoleWriter;

    public GenreShell(GenreService genreService, ConsoleWriter consoleWriter, ConsoleReader consoleReader) {
        this.genreService = genreService;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    @ShellMethod(value = "add genre", key = {"add_genre", "ag"})
    public void addGenre() {
        String title = consoleReader.read("Enter title of genre: ");
        Genre genre = Genre.builder().title(title).build();
        genreService.createGenre(genre);
        consoleWriter.write("Genre added.");

    }

    @ShellMethod(value = "show genre", key = {"show_genre", "sg"})
    public void showGenre() {
        List<Genre> genres = genreService.getAll();
        for (var genre : genres) {
            consoleWriter.writeEntityInfo(genre);
        }
    }

    @ShellMethod(value = "delete genre", key = {"delete_genre", "dg"})
    public void deleteGenre() {
        Long genreId = readGenreId();
        genreService.deleteGenre(genreId);
        consoleWriter.write("Genre deleted.");
    }

    @ShellMethod(value = "update genre", key = {"update_genre", "ug"})
    public void updateGenre() {
        Long genreId = readGenreId();
        String title = consoleReader.read("Enter title of genre: ");
        Genre genre = Genre.builder().id(genreId).title(title).build();
        genreService.updateGenre(genre);
        consoleWriter.write("Genre updated.");
    }

    @ShellMethod(value = "show single genre", key = {"show_single_genre", "ssg"})
    public void showSingleGenre() {
        Long genreId = readGenreId();
        Genre genre = genreService.getById(genreId);
        consoleWriter.writeEntityInfo(genre);
    }

    private Long readGenreId() {
        return consoleReader.readLong("Enter genre id: ");
    }
}