package com.belhard.bookstore.data.dao.impl.mapper;

import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.data.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Log4j2
public class UserRowMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(rs.getLong("user_id"));
        userDto.setFirstName(rs.getString("first_name"));
        userDto.setLastName(rs.getString("last_name"));
        userDto.setEmail(rs.getString("email"));
        userDto.setPassword(rs.getString("password"));
        userDto.setRole(Role.valueOf(rs.getString("name_role")));
        log.info("Created a user based on the results from the database");
        return userDto;
    }
}
