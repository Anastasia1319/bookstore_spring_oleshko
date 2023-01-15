package com.belhard.bookstore.data.repository;

import java.util.List;

public interface CrudRepository<T, K>{
    T findById (K key);
    List<T> findAll();
    void save (T entity);
    boolean delete (K key);
}
