package org.smart.bookstore.services;

import org.smart.bookstore.data.repositories.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    
    Optional<Book> findOneById(long id);
    
    List<Book> getAll();
    
    void save(Book t);
    
    void update(Book t, String[] params);
    
    void delete(Book t);

    Optional<Book> delete(long t);
}