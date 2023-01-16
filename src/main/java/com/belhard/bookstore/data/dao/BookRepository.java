package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Book b where b.deleted = false and b.isbn = :isbn")
    Optional<Book> findByIsbn (String isbn);

    @Query("from Book b where b.deleted = false and b.author = :author")
    List<Book> findByAuthor (String author);

    @Query("from Book b where b.deleted = false")
    List<Book> findAllAvailable ();

    @Query("from Book b where b.deleted = false and b.id = :id")
    Optional<Book> findAvailableById (Long id);
}
