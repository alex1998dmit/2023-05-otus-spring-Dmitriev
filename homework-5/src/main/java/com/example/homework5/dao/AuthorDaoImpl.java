package com.example.homework5.dao;

import com.example.homework5.domain.Author;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class AuthorDaoImpl implements AuthorDao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void createAuthor(Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", author.getFirstName())
                .addValue("lastName", author.getLastName())
                .addValue("middleName", author.getMiddleName());

        jdbcOperations.update(
                "insert into authors (`lastname`, `firstname`, `middlename`) " +
                        "values (:lastName, :firstName, :middleName)"
                , params);
    }

    @Override
    public void updateAuthor(Long authorId, Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", authorId)
                .addValue("firstName", author.getFirstName())
                .addValue("lastName", author.getLastName())
                .addValue("middleName", author.getMiddleName());
        jdbcOperations.update(
                "update authors " +
                        "set `lastName`=:lastName, `firstName`=:firstName, `middleName`=:middleName where id=:id",
                params);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", authorId);
        jdbcOperations.update(
                "delete from authors where id = :id",
                params
        );
    }

    @Override
    public Optional<Author> getById(Long authorId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", authorId);
        Author author = jdbcOperations.queryForObject(
                "select * from authors where id = :id",
                params, new AuthorMapper());
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query(
                "select * from authors order by id asc",
                new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String middleName = rs.getString("middleName");
            return new Author(id, firstName, lastName, middleName);
        }
    }
}
