package com.example.homework6.dao;

import com.example.homework6.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
