package com.example.homework5.dao;

import com.example.homework5.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void createBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthorId())
                .addValue("genre_id", book.getGenreId());
        jdbcOperations.update(
                "insert into " +
                        "books (`title`, `author_id`, `genre_id`) " +
                        "values (:title, :author_id, :genre_id)",
                params);
    }

    @Override
    public void updateBook(Long bookId, Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", bookId)
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthorId())
                .addValue("genre_id", book.getGenreId());
        jdbcOperations.update(
                "update books " +
                "set `title` = :title, `author_id` = :author_id, `genre_id` = :genre_id " +
                "where id = :id",
            params);
    }

    @Override
    public void deleteBook(Long bookId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", bookId);
        jdbcOperations.update("delete from books where id = :id", params);
    }

    @Override
    public Optional<Book> getById(Long bookId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", bookId);
        Book book = jdbcOperations.queryForObject(
                "select * from books where id = :id", params, new BookDaoImpl.BookMapper()
        );
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select * from books", new BookDaoImpl.BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            Long authorId = rs.getLong("author_id");
            Long genreId = rs.getLong("genre_id");
            return new Book(id, title, authorId, genreId);
        }
    }
}
