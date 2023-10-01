package com.example.homework6.service;

import com.example.homework6.dao.CommentDao;
import com.example.homework6.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = getById(commentId);
        commentDao.delete(comment);
    }

    @Override
    public Comment getById(String commentId) {
        Optional<Comment> comment = commentDao.findById(commentId);
        if (comment.isEmpty()) throw new RuntimeException("Not comment found");
        return comment.get();
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }
}