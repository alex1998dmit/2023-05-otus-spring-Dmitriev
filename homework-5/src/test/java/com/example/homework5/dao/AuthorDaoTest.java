package com.example.homework5.dao;

import com.example.homework5.domain.Author;
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
public class AuthorDaoTest {
    private final Author tolkien = Author.builder()
            .id(1L)
            .firstName("Tolkien")
            .lastName("John")
            .middleName("Ronald")
            .build();
    private final Author pushkin = Author.builder()
            .id(2L)
            .firstName("Pushkin")
            .lastName("Alexander")
            .middleName("Sergeevich")
            .build();
    private final Author turgenev = Author.builder()
            .id(3L)
            .firstName("Turgenev")
            .lastName("Ivan")
            .middleName("Sergeevich")
            .build();

    @Autowired
    private AuthorDao authorDao;

    @Test
    void getAuthorByIdTest() {
        Optional<Author> author = authorDao.getById(1L);
        assertTrue(author.isPresent());
        assert (author.get()).equals(tolkien);
    }

    @Test
    void getAuthorsTest() {
        List<Author> authorList = authorDao.getAll();
        assert (authorList).containsAll(List.of(tolkien, pushkin));
    }

    @Test
    void createAuthorTest() {
        authorDao.createAuthor(turgenev);
        List<Author> authorList = authorDao.getAll();
        assertThat(authorList).isEqualTo(List.of(tolkien, pushkin, turgenev));
        assertThat (authorList.size()).isEqualTo(3);
    }

    @Test
    void deleteAuthorTest() {
        Long authorId = 3L;
        authorDao.createAuthor(turgenev);
        authorDao.deleteAuthor(authorId);
        List<Author> authorList = authorDao.getAll();
        assertThat(authorList).isEqualTo(List.of(tolkien, pushkin));
    }

    @Test
    void updateAuthorTest() {
        Author rowling = Author.builder()
                .firstName("Rowling")
                .lastName("Joanne")
                .middleName("Katlin")
                .build();
        Long tolkienId = 1L;
        authorDao.updateAuthor(tolkienId, rowling);
        Optional<Author> newAuthor = authorDao.getById(tolkienId);
        rowling.setId(tolkienId);
        assertTrue(newAuthor.isPresent());
        assertThat(newAuthor.get()).isEqualTo(rowling);
    }
}
