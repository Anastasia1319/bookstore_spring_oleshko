package com.belhard.bookstore.service.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class BookDto {
    private Long id;
    private String author;
    private String title;
    private Integer publishinYear;
    private String isbn;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishinYear() {
        return publishinYear;
    }

    public void setPublishinYear(Integer publishinYear) {
        this.publishinYear = publishinYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return id.equals(bookDto.id) && Objects.equals(author, bookDto.author) && Objects.equals(title, bookDto.title) && Objects.equals(publishinYear, bookDto.publishinYear) && isbn.equals(bookDto.isbn) && Objects.equals(price, bookDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, publishinYear, isbn, price);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishinYear=" + publishinYear +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                '}';
    }
}
