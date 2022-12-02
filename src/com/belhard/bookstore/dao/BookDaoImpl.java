package com.belhard.bookstore.dao;

import com.belhard.bookstore.entity.Book;
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

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public  List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Book findById(Long id) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapResultSetToBook(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find book with id: " + id, e);
        }
        return book;
    }

    @Override
    public Book create(Book book) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            mapBookToStatementData(book, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return findById(id);
            }
            throw new RuntimeException("Couldn't create book: " + book);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't create book: " + book, e);
        }
    }

    @Override
    public Book update(Book book) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            mapBookToStatementData(book, statement);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't update book: " + book, e);
        }
        return findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't delete book with id: " + id, e);
        }
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ISBN)) {
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapResultSetToBook(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find book with isbn: " + isbn, e);
        }
        return book;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_AUTHOR)) {
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find book by author: " + author, e);
        }
        return books;
    }

    @Override
    public long countAll() {
        long count;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getLong("count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        return book;
    }

    private void mapBookToStatementData (Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getPublishinYear());
        statement.setString(4, book.getIsbn());
        statement.setBigDecimal(5, book.getPrice());
    }
}
