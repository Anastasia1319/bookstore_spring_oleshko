package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.Logging;
import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {
    private static final String FIND_ALL = "from Book where deleted = false";
    private static final String FIND_BY_ID = "from Book where id = :id and deleted = false";
    private static final String FIND_BY_ISBN = "from Book where isbn = :isbn and deleted = false";
    private static final String FIND_BY_AUTHOR = "from Book where author = :author and deleted = false";

    @PersistenceContext
    private EntityManager manager;

    @Logging
    @Override
    public  List<Book> findAll() {
        TypedQuery<Book> query = manager.createQuery(FIND_ALL, Book.class);
        return query.getResultList();
    }

    @Logging
    @Override
    public Optional<Book> findById(Long id) {
        TypedQuery<Book> query = manager.createQuery(FIND_BY_ID, Book.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Logging
    @Override
    public void save (Book entity) {
        if (entity.getId() != null) {
            manager.merge(entity);
        } else {
            manager.persist(entity);
        }
    }

    @Logging
    @Override
    public boolean delete(Long id) {
        Book book = manager.createQuery(FIND_BY_ID, Book.class).setParameter("id", id).getSingleResult();
        if (book == null) {
            return false;
        }
        book.setDeleted(true);
        manager.merge(book);
        return true;
    }

    @Logging
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        TypedQuery<Book> query = manager.createQuery(FIND_BY_ISBN, Book.class);
        query.setParameter("isbn", isbn);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Logging
    @Override
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = manager.createQuery(FIND_BY_AUTHOR, Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Logging
    @Override
    public long countAll() {
        return findAll().size();
    }
}
