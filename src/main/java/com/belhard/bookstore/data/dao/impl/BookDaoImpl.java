package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.connection.DataSource;
import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final String SELECT_ALL = "SELECT * FROM books";
    private static final String FIND_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String CREATE = "INSERT INTO books (author, title, publishin_year, isbn, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET author = ?, title = ?, publishin_year = ?, isbn = ?, price = ?)";
    private static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String FIND_BY_ISBN = "SELECT * FROM books WHERE isbn = ?";
    private static final String FIND_BY_AUTHOR = "SELECT * FROM books WHERE author = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM books";
    private final DataSource dataSource;
    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public  List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            log.info("Connected to URL");
            while (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException(e);
        }
        log.info("Created a list of books matching the search criteria");
        return books;
    }

    @Override
    public Book findById(Long id) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            log.info("Connected to URL");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapResultSetToBook(resultSet);
                log.info("Book matching search criteria: {}", book);
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't find book with id: " + id, e);
        }
        return book;
    }

    @Override
    public Book create(Book book) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            log.info("Connected to URL");
            mapBookToStatementData(book, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                log.info("Book {} created", book);
                return findById(id);
            }
            log.warn("Book {} wasn't created", book);
            throw new NotFoundException("Couldn't create book: " + book);
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't create book: " + book, e);
        }
    }

    @Override
    public Book update(Book book) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            log.info("Connected to URL");
            mapBookToStatementData(book, statement);
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't update book: " + book, e);
        }
        log.info("Book {} updated", book);
        return findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            log.info("Connected to URL");
            statement.setLong(1, id);
            boolean deletedResult = (statement.executeUpdate() == 1);
            if (deletedResult) {
                log.info("Book with id {} deleted", id);
            } else {
                log.info("Book with id {} was not delete", id);
            }
            return deletedResult;
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't delete book with id: " + id, e);
        }
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ISBN)) {
            log.info("Connected to URL");
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapResultSetToBook(resultSet);
                log.info("Book matching search criteria: {}", book);
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't find book with isbn: " + isbn, e);
        }
        return book;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_AUTHOR)) {
            log.info("Connected to URL");
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't find book by author: " + author, e);
        }
        log.info("Created a list of books matching the search criteria");
        return books;
    }

    @Override
    public long countAll() {
        long count;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(COUNT_ALL)) {
            log.info("Connected to URL");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getLong("count");
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException(e);
        }
        log.info("Number of objects in the database: {}", count);
        return count;
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setPublishinYear(resultSet.getInt("publishin_year"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPrice(resultSet.getBigDecimal("price"));
        log.info("Created a book based on the results from the database");
        return book;
    }

    private void mapBookToStatementData (Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getPublishinYear());
        statement.setString(4, book.getIsbn());
        statement.setBigDecimal(5, book.getPrice());
        log.info("Object prepared for transfer to the database");
    }
}
