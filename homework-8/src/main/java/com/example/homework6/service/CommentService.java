package com.example.homework6.service;

import com.example.homework6.domain.Comment;
import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(String commentID);

    Comment getById(String commentId);

    List<Comment> getAll();
}
