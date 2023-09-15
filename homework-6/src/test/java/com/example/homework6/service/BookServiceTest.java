package com.example.homework6.service;

import com.example.homework6.dao.*;
import com.example.homework6.domain.Book;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertThrows;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {
    @Mock
    private BookDao bookDao;

    @Mock
    private GenreDao genreDao;

    @Mock
    private AuthorDao authorDao;

    @Mock
    BufferedReader bufferedReader;

    private BookService bookService;

    private final Book bookTest1 = Book.builder().id(1L).title("lord of the rings").genre(GenreDaoTest.fantasy).author(AuthorDaoTest.tolkien).build();

    private final Book bookTest2 = Book.builder().id(2L).title("harry potter").genre(GenreDaoTest.fantasy).author(AuthorDaoTest.pushkin).build();

    @BeforeEach
    public void init() {
        Mockito.when(authorDao.getAll()).thenReturn(
                List.of(
                        AuthorDaoTest.tolkien,
                        AuthorDaoTest.pushkin
                )
        );
        Mockito.when(genreDao.getAll()).thenReturn(
                List.of(
                        GenreDaoTest.fantasy,
                        GenreDaoTest.lyrics
                )
        );
        Mockito.when(bookDao.getAll()).thenReturn(
                List.of(
                        bookTest1,
                        bookTest2
                )
        );
        Mockito.when(bookDao.getById(1L)).thenReturn(bookTest1);
        Mockito.when(bookDao.getById(2L)).thenReturn(bookTest2);
        Mockito.when(bookDao.getById(3L)).thenReturn(null);

        ConsoleReader consoleReader = new ConsoleReaderImpl(bufferedReader);
        bookService = new BookServiceImpl(bookDao, authorDao, genreDao, consoleReader);
    }

    @Test
    public void getAllBookTest() {
        List<Book> books = bookService.getAll();
        assert (books).containsAll(List.of(bookTest1, bookTest2));
    }

    @Test
    public void getBookByIdSuccess() throws IOException {
        Long book1id = 1L;
        Mockito.when(bufferedReader.readLine())
                .thenReturn(String.valueOf(book1id));

        Book book1 = bookService.getById();
        Assertions.assertNotNull(book1);
        assert (book1).equals(bookTest1);
    }

    @Test
    public void getBookByIdNotFound() throws IOException {
        assertThrows(NullPointerException.class,
                () -> {
                    Long book1id = 3L;
                    Mockito.when(bufferedReader.readLine())
                            .thenReturn(String.valueOf(book1id));

                    Book book1 = bookService.getById();
                });
    }
}
