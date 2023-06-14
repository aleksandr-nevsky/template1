package com.example.demo.dao.impl;

import com.example.demo.dao.SystemUserDao;
import com.example.demo.model.SystemUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class SystemUserDaoImpl implements SystemUserDao {

    private final NamedParameterJdbcOperations jdbc;

    public SystemUserDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<SystemUser> getAll() {
        return jdbc.query("SELECT * FROM system_user", new SystemUserMapper());
    }

    @Override
    public SystemUser getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);

        return jdbc.queryForObject(
                "SELECT * FROM system_user WHERE id = :id",
                params,
                new SystemUserMapper()
        );
    }

    @Override
    public long insert(SystemUser systemUser) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(systemUser.getPassword());
        StringJoiner roles = new StringJoiner(",");
        systemUser.getRoles().forEach(roles::add);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", systemUser.getUsername());
        params.addValue("password", encryptedPassword);
        params.addValue("roles", roles.toString());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO system_user (username, password, roles) values (:username, :password, :roles)",
                params,
                keyHolder,
                new String[]{"id"}
        );

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private static class SystemUserMapper implements RowMapper<SystemUser> {

        @Override
        public SystemUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String roles = rs.getString("roles");

            String[] split = roles.split(",");
            List<String> rolesSplit = Arrays.asList(split);
            return SystemUser.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .roles(rolesSplit).build();
        }
    }
}
