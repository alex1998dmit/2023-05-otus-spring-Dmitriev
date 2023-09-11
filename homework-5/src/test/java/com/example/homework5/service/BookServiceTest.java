package com.example.homework5.service;

import com.example.homework5.dao.BookDao;
import com.example.homework5.domain.Book;
import com.example.homework5.utils.ConsoleReader;
import com.example.homework5.utils.ConsoleReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @Mock
    BufferedReader bufferedReader;

    private BookService bookService;

    private final Book bookTest1 = Book.builder().id(1L).title("lord of the rings").genreId(1L).authorId(1L).build();

    private final Book bookTest2 = Book.builder().id(2L).title("harry potter").genreId(2L).authorId(2L).build();

    @BeforeEach
    public void init() {
        Mockito.when(bookDao.getAll()).thenReturn(
                List.of(
                        bookTest1,
                        bookTest2
                )
        );
        Mockito.when(bookDao.getById(1L)).thenReturn(Optional.of(bookTest1));
        Mockito.when(bookDao.getById(2L)).thenReturn(Optional.of(bookTest2));
        Mockito.when(bookDao.getById(3L)).thenReturn(Optional.empty());

        ConsoleReader consoleReader = new ConsoleReaderImpl(bufferedReader);
        bookService = new BookServiceImpl(bookDao, consoleReader);
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

        Optional<Book> book1 = bookService.getById();
        assertTrue(book1.isPresent());
        assert (book1.get()).equals(bookTest1);
    }

    @Test
    public void getBookByIdNotFound() throws IOException {
        Long book1id = 3L;
        Mockito.when(bufferedReader.readLine())
                .thenReturn(String.valueOf(book1id));

        Optional<Book> book1 = bookService.getById();
        assertFalse(book1.isPresent());
    }
}
