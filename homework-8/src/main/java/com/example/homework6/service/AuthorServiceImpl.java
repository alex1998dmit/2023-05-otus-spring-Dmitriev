package com.example.homework6.service;

import com.example.homework6.dao.AuthorDao;
import com.example.homework6.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        checkAuthorExits(author.getId());
        return authorDao.save(author);
    }

    @Override
    public void deleteAuthor(String authorId) {
        Author author = getById(authorId);
        authorDao.delete(author);
    }

    @Override
    public Author getById(String authorId) {
        Optional<Author> author = authorDao.findById(String.valueOf(authorId));
        if (author.isEmpty()) throw new RuntimeException("Not author found");
        return author.get();
    }

    @Override
    public List<Author> getAll() {
        return authorDao.findAll();
    }

    public void checkAuthorExits(String authorId) {
        boolean isExists = authorDao.existsById(authorId);
        if (!isExists) throw new RuntimeException("Not found author");
    }
}