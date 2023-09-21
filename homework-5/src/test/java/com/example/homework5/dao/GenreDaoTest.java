package com.example.homework5.dao;

import com.example.homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreDaoTest {

    private final Genre lyrics = Genre.builder()
            .id(1L)
            .title("Lyrics")
            .build();

    private final Genre fantasy = Genre.builder()
            .id(2L)
            .title("Fantasy")
            .build();

    private final Genre tragedy = Genre.builder()
            .id(3L)
            .title("Tragedy")
            .build();

    @Autowired
    private GenreDao genreDao;

    @Test
    void getGenreByIdTest() {
        Optional<Genre> genre = genreDao.getById(1L);
        assertTrue(genre.isPresent());
        assert (genre.get()).equals(lyrics);
    }

    @Test
    void getGenresTest() {
        List<Genre> genreList = genreDao.getAll();
        for (var genre : genreList) {
            System.out.println(genre.getTitle());
        }
        assert (genreList).containsAll(List.of(lyrics, fantasy));
    }

    @Test
    void createAuthorTest() {
        genreDao.createGenre(tragedy);
        List<Genre> genreList = genreDao.getAll();
        assertThat(genreList).isEqualTo(List.of(lyrics, fantasy, tragedy));
        assertThat (genreList.size()).isEqualTo(3);
    }

    @Test
    void deleteGenreTest() {
        Long genreId = 3L;
        genreDao.createGenre(tragedy);
        genreDao.deleteGenre(genreId);
        List<Genre> genreList = genreDao.getAll();
        assertThat(genreList).isEqualTo(List.of(lyrics, fantasy));
    }

    @Test
    void updateGenreTest() {
        Genre comedy = Genre.builder()
                .title("Comedy")
                .build();
        Long novelId = 1L;
        comedy.setId(novelId);
        genreDao.updateGenre(novelId, comedy);
        Optional<Genre> genre = genreDao.getById(novelId);
        assertTrue(genre.isPresent());
        assertThat(genre.get()).isEqualTo(comedy);
    }
}
