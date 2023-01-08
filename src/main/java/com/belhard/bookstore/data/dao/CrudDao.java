package com.belhard.bookstore.data.dao;

import java.util.List;

public interface CrudDao <T, K>{
    T findById (K key);
    List<T> findAll();
    T create (T entity);
    T update (T entity);
    boolean delete (K key);
}
