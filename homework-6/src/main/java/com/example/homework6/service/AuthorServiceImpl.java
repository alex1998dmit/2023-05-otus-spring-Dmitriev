package com.example.homework6.service;

import com.example.homework6.dao.AuthorDao;
import com.example.homework6.domain.Author;
import com.example.homework6.utils.ConsoleReader;
import com.example.homework6.utils.ConsoleReaderImpl;
import com.example.homework6.utils.ConsoleWriter;
import com.example.homework6.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    private final ConsoleWriter consoleWriter = new ConsoleWriterImpl();

    private ConsoleReader consoleReader = new ConsoleReaderImpl();

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public AuthorServiceImpl(AuthorDao authorDao, ConsoleReader consoleReader) {
        this.authorDao = authorDao;
        this.consoleReader = consoleReader;
    }

    @Override
    public void createAuthor() {
        String firstName = consoleReader.read("Enter author firstName: ");
        String lastName = consoleReader.read("Enter author middleName: ");
        String middleName = consoleReader.read("Enter author middleName: ");
        authorDao.createAuthor(
                Author.builder().firstName(firstName).lastName(lastName).middleName(middleName).build()
        );
        consoleWriter.write("Author added.");
    }

    @Override
    public void updateAuthor() {
        Long authorId = consoleReader.readLong("Enter author id: ");
        String firstName = consoleReader.read("Enter author firstName: ");
        String lastName = consoleReader.read("Enter author middleName: ");
        String middleName = consoleReader.read("Enter author middleName: ");
        Author newAuthor = Author.builder().id(authorId).firstName(firstName).lastName(lastName).middleName(middleName).build();
        authorDao.updateAuthor(newAuthor);
        consoleWriter.write("Author updated.");
    }

    @Override
    public void deleteAuthor() {
        Long authorId = readAuthorId();
        authorDao.deleteAuthor(authorId);
        consoleWriter.write("Author deleted.");
    }

    @Override
    public void getById() {
        Long authorId = readAuthorId();
        Author author = authorDao.getById(authorId);
        printAuthorInfo(author);
    }

    @Override
    public void getAll() {
        List<Author> authors = authorDao.getAll();
        for (var author : authors) {
            printAuthorInfo(author);
        }
    }

    private Long readAuthorId() {
        return consoleReader.readLong("Enter author id: ");
    }

    private void printAuthorInfo(Author author) {
            consoleWriter.write(
                    author.getId() + " - " + author.getFullName()
            );
    }
}
