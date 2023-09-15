package com.example.homework6.dao;

import com.example.homework6.domain.Comment;
import java.util.List;

public interface CommentDao {
    Comment createComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Long commentId);

    Comment getById(Long commentId);

    List<Comment> getAll();
}
