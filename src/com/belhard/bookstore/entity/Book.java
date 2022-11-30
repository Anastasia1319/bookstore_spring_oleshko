package com.belhard.bookstore.entity;

import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String title;
    private Integer publishinYear;
    private String isbn;
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(publishinYear, book.publishinYear) && isbn.equals(book.isbn) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, publishinYear, isbn, price);
    }

    @Override
    public String toString() {
        return "com.belhard.bookstore.entity.Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishinYear=" + publishinYear +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                '}';
    }
}
