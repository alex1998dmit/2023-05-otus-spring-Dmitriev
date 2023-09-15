package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
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

@DisplayName("BookRepository tests")
@DataJpaTest
@Import({BookDaoImpl.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("book-dataset.xml")
public class BookDaoTest {
    private final Author tolkien = Author.builder()
            .id(1000L)
            .firstName("Tolkien")
            .lastName("John")
            .middleName("Ronald")
            .build();

    private final Genre fantasy = Genre.builder()
            .id(2000L)
            .title("Fantasy")
            .build();

    public static final Book lordOfTheRings = Book.builder()
            .id(1000L)
            .title("Lord of the rings")
            .author(AuthorDaoTest.tolkien)
            .genre(GenreDaoTest.fantasy)
            .build();

    private final Book hobbit = Book.builder()
            .title("Hobbit")
            .author(tolkien)
            .genre(fantasy)
            .build();

    @Autowired
    private BookDao bookDao;

    @Test
    void getBookByIdTest() {
        Book book = bookDao.getById(1000L);
        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(1000L);
    }

    @Test
    void getBooksTest() {
        List<Book> bookList = bookDao.getAll();
        assertThat(bookList).hasSize(2);
        assertThat(bookList.get(0).getId()).isEqualTo(1000L);
        assertThat(bookList.get(1).getId()).isEqualTo(2000L);
    }

    @Test
    void deleteBookTest() {
        List<Book> bookList;
        Book addedHobbit = bookDao.createBook(hobbit);
        bookList = bookDao.getAll();
        assertThat(bookList).hasSize(3);

        bookDao.deleteBook(addedHobbit.getId());
        bookList = bookDao.getAll();
        assertThat(bookList).hasSize(2);
        assertThat(bookDao.getById(addedHobbit.getId())).isNull();
    }

    @Test
    void createBookTest() {
        Book addedHobbit = bookDao.createBook(hobbit);
        hobbit.setId(addedHobbit.getId());
        List<Book> bookList = bookDao.getAll();
        assertThat(bookList).hasSize(3);
        assertThat(bookDao.getById(addedHobbit.getId())).isNotNull();
    }

    @Test
    void updateAuthorTest() {
        Long tolkienId = 1000L;
        Book silmaryion = Book.builder()
                .id(tolkienId)
                .title("Silmaryion")
                .author(AuthorDaoTest.tolkien)
                .genre(GenreDaoTest.fantasy)
                .build();
        bookDao.updateBook(silmaryion);
        Book newBook = bookDao.getById(tolkienId);
        assertThat(newBook.getTitle()).isEqualTo(silmaryion.getTitle());
        assertThat(newBook.getId()).isEqualTo(silmaryion.getId());
    }
}
