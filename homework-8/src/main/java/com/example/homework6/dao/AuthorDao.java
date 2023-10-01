package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuthorDao extends MongoRepository<Author, String> {
}