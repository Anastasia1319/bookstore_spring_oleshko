package com.belhard.bookstore.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author", length = 300)
    private String author;

    @Column(name = "title", length = 300)
    private String title;

    @Column(name = "publishing_year")
    private Integer publishingYear;

    @Column(name = "isbn", length = 13, nullable = false, unique = true)
    private String isbn;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "deleted", columnDefinition = "FALSE")
    private boolean deleted;
}
