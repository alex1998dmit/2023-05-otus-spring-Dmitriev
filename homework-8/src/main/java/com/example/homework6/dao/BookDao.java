package com.example.homework6.dao;

import com.example.homework6.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookDao extends MongoRepository<Book, String> {
}