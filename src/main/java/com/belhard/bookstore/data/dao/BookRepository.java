package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Book b where b.deleted = false and b.isbn = :isbn")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("from Book b where b.deleted = false and b.author = :author")
    List<Book> findByAuthor(@Param("author") String author, Pageable pageable);

    @Query("from Book b where b.deleted = false")
    List<Book> findAllAvailable(Pageable pageable);

    @Query("from Book b where b.deleted = false and b.id = :id")
    Optional<Book> findAvailableById(@Param("id") Long id);

    Integer countByAuthor(String author);

    Integer countAllByDeletedFalse();
}
