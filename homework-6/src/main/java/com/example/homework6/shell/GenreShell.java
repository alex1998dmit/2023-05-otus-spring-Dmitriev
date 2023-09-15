package com.example.homework6.shell;

import com.example.homework6.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GenreShell {
    private final GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "add genre", key = {"add_genre", "ag"})
    public void addGenre() {
        genreService.createGenre();
    }

    @ShellMethod(value = "show genre", key = {"show_genre", "sg"})
    public void showGenre() {
        genreService.getAll();
    }

    @ShellMethod(value = "delete genre", key = {"delete_genre", "dg"})
    public void deleteGenre() {
        genreService.deleteGenre();
    }

    @ShellMethod(value = "update genre", key = {"update_genre", "ug"})
    public void updateGenre() {
        genreService.updateGenre();
    }

    @ShellMethod(value = "show single genre", key = {"show_single_genre", "ssg"})
    public void showSingleGenre() {
        genreService.getById();
    }
}
