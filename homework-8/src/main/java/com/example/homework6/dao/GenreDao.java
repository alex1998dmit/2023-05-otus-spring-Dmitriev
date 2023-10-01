package com.example.homework6.dao;

import com.example.homework6.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreDao extends MongoRepository<Genre, String> {
}