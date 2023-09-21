package com.example.homework6.dao;

import com.example.homework6.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
