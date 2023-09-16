package com.example.homework5.dao;

import com.example.homework5.domain.Genre;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class GenreDaoImpl implements GenreDao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void createGenre(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", genre.getTitle());
        jdbcOperations.update("insert into genres (`title`) values (:title)", params);
    }

    @Override
    public void updateGenre(Long genreId, Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("title", genre.getTitle());
        jdbcOperations.update("update genres set title = :title where id = :id", params);
    }

    @Override
    public void deleteGenre(Long genreId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", genreId);
        jdbcOperations.update("delete from genres where id = :id", params);
    }

    @Override
    public Optional<Genre> getById(Long genreId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", genreId);
        Genre genre = jdbcOperations.queryForObject(
                "select id, title from genres where id = :id",
                params, new GenreDaoImpl.GenreMapper()
        );
        return Optional.ofNullable(genre);
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query(
                "select id, title from genres order by id asc",
                new GenreDaoImpl.GenreMapper()
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            return new Genre(id, title);
        }
    }
}
