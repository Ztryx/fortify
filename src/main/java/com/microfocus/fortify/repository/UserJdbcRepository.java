package com.microfocus.fortify.repository;

/**
 * Created by andyx on 12/5/18.
 */

import com.microfocus.fortify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }

    public User findById(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from user where id=?", new Object[]{id},
                    new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("select * from user where email=?", new Object[] { email },
                    new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from user where id=?", new Object[] { id });
    }

    public int insert(User user) {
        return jdbcTemplate.update("insert into user (name, email) " + "values(?, ?)",
                new Object[] { user.getName(), user.getEmail() });
    }

    public int update(User user) {
        return jdbcTemplate.update("update user " + " set name = ?, email = ? " + " where id = ?",
                new Object[] { user.getName(), user.getEmail(), user.getId() });
    }

}
