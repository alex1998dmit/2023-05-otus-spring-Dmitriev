package com.example.homework6.service;

import com.example.homework6.domain.Comment;
import java.util.List;

public interface CommentService {
    void createComment();

    void updateComment();

    void deleteComment();

    Comment getById();

    List<Comment> getAll();
}
