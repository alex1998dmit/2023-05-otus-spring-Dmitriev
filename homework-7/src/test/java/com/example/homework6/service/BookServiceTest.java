package com.example.homework6.service;

import com.example.homework6.dao.BookDao;
import com.example.homework6.domain.Author;
import com.example.homework6.domain.Book;
import com.example.homework6.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {
    @Mock
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    private static final Author tolkien = Author.builder()
            .id(1000L)
            .firstName("Tolkien")
            .lastName("John")
            .middleName("Ronald")
            .build();

    private static final Genre fantasy = Genre.builder()
            .id(2000L)
            .title("Fantasy")
            .build();

    public static final Book lordOfTheRings = Book.builder()
            .id(1000L)
            .title("Lord of the rings")
            .author(tolkien)
            .genre(fantasy)
            .build();

    private final Book hobbit = Book.builder()
            .title("Hobbit")
            .author(tolkien)
            .genre(fantasy)
            .build();

    @BeforeEach
    public void init() {
        when(bookDao.findAll()).thenReturn(
                List.of(
                        lordOfTheRings,
                        hobbit
                )
        );
        when(bookDao.findById(1000L)).thenReturn(Optional.of(lordOfTheRings));
        when(bookDao.save(hobbit)).thenReturn(hobbit);
        when(bookDao.existsById(1000L)).thenReturn(true);
        when(bookDao.existsById(2000L)).thenReturn(false);
        bookService = new BookServiceImpl(bookDao);
    }

    @Test
    public void getAllBookTest() {
        List<Book> books = bookService.getAll();
        assertThat(books).containsAll(List.of(lordOfTheRings, hobbit));
    }

    @Test
    public void getBookByIdSuccess() {
        Long book1id = 1000L;
        Book book1 = bookService.getById(book1id);
        assertThat(book1).isEqualTo(lordOfTheRings);
    }

    @Test
    public void getBookByIdNotFound() {
        Long book1id = 2000L;
        assertThrows(RuntimeException.class, () -> bookService.getById(book1id));
    }

    @Test
    public void addNewBook() {
        Book newBook = bookService.createBook(hobbit);
        verify(bookDao, times(1)).save(hobbit);
        assertThat(newBook).isEqualTo(hobbit);
    }
}
