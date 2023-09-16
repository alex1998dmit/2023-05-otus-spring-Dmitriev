package com.example.homework5.service;

import com.example.homework5.dao.AuthorDao;
import com.example.homework5.domain.Author;
import com.example.homework5.utils.ConsoleReader;
import com.example.homework5.utils.ConsoleReaderImpl;
import com.example.homework5.utils.ConsoleWriter;
import com.example.homework5.utils.ConsoleWriterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        Author newAuthor = Author.builder().firstName(firstName).lastName(lastName).middleName(middleName).build();
        authorDao.updateAuthor(
                authorId,
                newAuthor
        );
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
        Optional<Author> author = authorDao.getById(authorId);
        author.ifPresent(consoleWriter::writeEntityInfo);
    }

    @Override
    public void getAll() {
        List<Author> authors = authorDao.getAll();
        for (var author : authors) {
            consoleWriter.writeEntityInfo(author);
        }
    }

    private Long readAuthorId() {
        return consoleReader.readLong("Enter author id: ");
    }
}
