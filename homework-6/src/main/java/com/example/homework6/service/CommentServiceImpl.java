package com.example.homework6.service;

import com.example.homework6.dao.CommentDao;
import com.example.homework6.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        return commentDao.createComment(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long commentId) {
        return commentDao.getById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return commentDao.getAll();
    }
}
