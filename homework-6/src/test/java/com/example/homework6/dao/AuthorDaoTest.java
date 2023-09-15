package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@DisplayName("BookCommentRepository tests")
@DataJpaTest
@Import(AuthorDaoImpl.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("author-dataset.xml")
public class AuthorDaoTest {
    public static final Author tolkien = Author.builder()
            .firstName("Tolkien")
            .lastName("John")
            .middleName("Ronald")
            .build();

    public static final Author pushkin = Author.builder()
            .firstName("Pushkin")
            .lastName("Alexander")
            .middleName("Sergeevich")
            .build();

    private final Author turgenev = Author.builder()
            .firstName("Turgenev")
            .lastName("Ivan")
            .middleName("Sergeevich")
            .build();

    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    void getAuthorsTest() {
        List<Author> authorList = authorDao.getAll();
        assertThat(authorList).hasSize(2);
        assertThat(authorList.get(0).getId()).isEqualTo(1000L);
        assertThat(authorList.get(1).getId()).isEqualTo(2000L);
    }

    @Test
    void getAuthorByIdTest() {
        Author author = authorDao.getById(1000L);
        assertThat(author).isNotNull();
        assertThat(author.getId()).isEqualTo(1000L);
    }

    @Test
    void deleteAuthorTest() {
        List<Author> authorList;
        Author addedTurgenev = authorDao.createAuthor(turgenev);
        authorList = authorDao.getAll();
        turgenev.setId(addedTurgenev.getId());
        assertThat(authorList).hasSize(3);

        authorDao.deleteAuthor(addedTurgenev.getId());
        authorList = authorDao.getAll();
        assertThat(authorList).hasSize(2);
        assertThat(authorDao.getById(addedTurgenev.getId())).isNull();
    }

    @Test
    void updateAuthorTest() {
        Long tolkienId = 1000L;
        Author rowling = Author.builder()
                .id(tolkienId)
                .firstName("Rowling")
                .lastName("Joanne")
                .middleName("Katlin")
                .build();
        authorDao.updateAuthor(rowling);
        Author newAuthor = authorDao.getById(tolkienId);
        rowling.setId(tolkienId);
        assertThat(newAuthor).isEqualTo(rowling);
    }

    @Test
    void createAuthorTest() {
        Author addedTurgenev = authorDao.createAuthor(turgenev);
        turgenev.setId(addedTurgenev.getId());
        List<Author> authorList = authorDao.getAll();
        assertThat(authorList).hasSize(3);
        assertThat(authorDao.getById(addedTurgenev.getId())).isNotNull();
    }
}
