package com.example.homework5.dao;

import com.example.homework5.domain.Book;
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
public class BookDaoTest {
    private final Book hobbit = Book.builder()
            .id(3L)
            .title("Hobbit")
            .genreId(2L)
            .authorId(1L)
            .build();
    private final Book lordOfTheRings = Book.builder()
            .id(1L)
            .title("Lord of the rings")
            .authorId(1L)
            .genreId(2L)
            .build();
    private final Book uLukomoriye = Book.builder()
            .id(2L)
            .title("U Lukomorya")
            .authorId(2L)
            .genreId(1L)
            .build();

    @Autowired
    private BookDao bookDao;

    @Test
    void getBookByIdTest() {
        Optional<Book> book = bookDao.getById(1L);
        assertTrue(book.isPresent());
        assert (book.get()).equals(lordOfTheRings);
    }

    @Test
    void getBooksTest() {
        List<Book> bookList = bookDao.getAll();
        assert (bookList).containsAll(List.of(lordOfTheRings, uLukomoriye));
    }

    @Test
    void createAuthorTest() {
        bookDao.createBook(hobbit);
        List<Book> bookList = bookDao.getAll();
        assertThat(bookList).isEqualTo(List.of(lordOfTheRings, uLukomoriye, hobbit));
        assertThat (bookList.size()).isEqualTo(3);
    }

    @Test
    void deleteAuthorTest() {
        Long bookId = 3L;
        bookDao.createBook(hobbit);
        bookDao.deleteBook(bookId);
        List<Book> bookList = bookDao.getAll();
        assertThat(bookList).isEqualTo(List.of(lordOfTheRings, uLukomoriye));
    }

    @Test
    void updateAuthorTest() {
        Book silmaryion = Book.builder()
                .title("Silmaryion")
                .authorId(1L)
                .genreId(2L)
                .build();
        Long tolkienId = 1L;
        silmaryion.setId(tolkienId);
        bookDao.updateBook(tolkienId, silmaryion);
        Optional<Book> book = bookDao.getById(tolkienId);
        assertTrue(book.isPresent());
        assertThat(book.get()).isEqualTo(silmaryion);
    }
}
