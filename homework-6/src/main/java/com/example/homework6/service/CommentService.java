package com.example.homework6.service;

import com.example.homework6.domain.Comment;
import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long commentID);

    Comment getById(Long commentId);

    List<Comment> getAll();
}
