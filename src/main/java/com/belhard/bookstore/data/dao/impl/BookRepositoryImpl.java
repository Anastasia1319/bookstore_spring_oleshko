package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@Transactional
public class BookRepositoryImpl implements BookRepository {
    private static final String FIND_ALL = "from Book where deleted = false";
    private static final String FIND_BY_ID = "from Book where id = :id and deleted = false";
    private static final String FIND_BY_ISBN = "from Book where isbn = :isbn and deleted = false";
    private static final String FIND_BY_AUTHOR = "from Book where author = :author and deleted = false";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public  List<Book> findAll() {
        log.info("Created a list of books matching the search criteria");
        TypedQuery<Book> query = manager.createQuery(FIND_ALL, Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        log.info("Book with id {} found", id);
        TypedQuery<Book> query = manager.createQuery(FIND_BY_ID, Book.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public void save (Book entity) {
        log.info("Trying to update a row with a book in the database");
        if (entity.getId() != null) {
            log.info("Update the book");
            manager.merge(entity);
        } else {
            log.info("Creat the book");
            manager.persist(entity);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.info("Trying to delete a row with a book in the database");
        Book book = manager.createQuery(FIND_BY_ID, Book.class).setParameter("id", id).getSingleResult();
        if (book == null) {
            log.warn("Updated rows on deletion (books): 0");
            return false;
        }
        book.setDeleted(true);
        manager.merge(book);
        return true;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        log.info("Book with isbn {} found", isbn);
        TypedQuery<Book> query = manager.createQuery(FIND_BY_ISBN, Book.class);
        query.setParameter("isbn", isbn);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        log.info("Created a list of books matching the search criteria");
        TypedQuery<Book> query = manager.createQuery(FIND_BY_AUTHOR, Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public long countAll() {
        log.info("Created a database call - counting the number of rows");
        return findAll().size();
    }
}
