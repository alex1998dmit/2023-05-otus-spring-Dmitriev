package com.example.homework6.service;

import com.example.homework6.dao.AuthorDao;
import com.example.homework6.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    @Transactional
    public Author createAuthor(Author author) {
        return authorDao.createAuthor(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        return authorDao.updateAuthor(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        authorDao.deleteAuthor(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Long authorId) {
        return authorDao.getById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}