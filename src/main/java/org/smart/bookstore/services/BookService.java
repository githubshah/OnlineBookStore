package org.smart.bookstore.services;

import org.smart.bookstore.data.repositories.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService<T> {
    
    Optional<T> get(long id);
    
    List<Book> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}