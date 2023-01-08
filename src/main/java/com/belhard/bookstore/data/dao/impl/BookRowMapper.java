package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dto.BookDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Log4j2
public class BookRowMapper implements RowMapper<BookDto> {

    @Override
    public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(rs.getLong("id"));
        bookDto.setAuthor(rs.getString("author"));
        bookDto.setTitle(rs.getString("title"));
        bookDto.setPublishinYear(rs.getInt("publishin_year"));
        bookDto.setIsbn(rs.getString("isbn"));
        bookDto.setPrice(rs.getBigDecimal("price"));
        log.info("Created a book based on the results from the database");
        return bookDto;
    }
}
