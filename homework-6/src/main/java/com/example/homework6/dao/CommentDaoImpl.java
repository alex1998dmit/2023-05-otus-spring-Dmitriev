package com.example.homework6.dao;

import com.example.homework6.domain.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment createComment(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        entityManager.merge(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = entityManager.find(Comment.class, commentId);
        entityManager.remove(comment);
    }

    @Override
    public Comment getById(Long commentId) {
        return entityManager.find(Comment.class, commentId);
    }

    @Override
    public List<Comment> getAll() {
        return entityManager.createQuery("from Comment ", Comment.class).getResultList();
    }
}
