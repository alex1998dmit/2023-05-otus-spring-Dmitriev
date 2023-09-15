package com.example.homework6.dao;

import com.example.homework6.domain.Genre;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GenreRepository tests")
@DataJpaTest
@Import({GenreDaoImpl.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("genre-dataset.xml")
public class GenreDaoTest {
    public static final Genre lyrics = Genre.builder()
            .id(1000L)
            .title("Lyrics")
            .build();

    public static final Genre fantasy = Genre.builder()
            .id(2000L)
            .title("Fantasy")
            .build();

    private final Genre tragedy = Genre.builder()
            .title("Tragedy")
            .build();

    @Autowired
    private GenreDao genreDao;

    @Test
    void getGenresTest() {
        List<Genre> genres = genreDao.getAll();
        assertThat(genres).hasSize(2).containsExactly(lyrics, fantasy);
    }

    @Test
    void getGenreByIdTest() {
        Genre genre = genreDao.getById(1000L);
        assertThat(genre).isEqualTo(lyrics);
    }

    @Test
    void deleteAuthorTest() {
        List<Genre> genresList;
        Genre addedTragedy = genreDao.createGenre(tragedy);
        genresList = genreDao.getAll();
        tragedy.setId(addedTragedy.getId());
        assertThat(genresList).hasSize(3).containsExactly(tragedy, lyrics, fantasy);

        genreDao.deleteGenre(addedTragedy.getId());
        genresList = genreDao.getAll();
        assertThat(genresList).hasSize(2).containsExactly(lyrics, fantasy);
    }

    @Test
    void updateGenreTest() {
        Long novelId = 1000L;
        Genre comedy = Genre.builder()
                .id(novelId)
                .title("Comedy")
                .build();
        genreDao.updateGenre(comedy);
        Genre newGenre = genreDao.getById(novelId);
        comedy.setId(novelId);
        assertThat(newGenre).isEqualTo(comedy);
    }

    @Test
    void createGenreTest() {
        Genre addedGenre = genreDao.createGenre(tragedy);
        tragedy.setId(addedGenre.getId());
        List<Genre> genreList = genreDao.getAll();
        assertThat(genreList).hasSize(3).containsExactly(tragedy, lyrics, fantasy);
    }
}
