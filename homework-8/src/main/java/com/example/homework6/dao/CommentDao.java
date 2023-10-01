package com.example.homework6.dao;

import com.example.homework6.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment, String> {
}