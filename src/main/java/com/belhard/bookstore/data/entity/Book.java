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

    @Column(name = "author", length = 50)
    private String author;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "publishin_year", length = 4)
    private Integer publishinYear;

    @Column(name = "isbn", length = 13, nullable = false, unique = true)
    private String isbn;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "deleted", columnDefinition = "FALSE")
    private boolean deleted;
}
