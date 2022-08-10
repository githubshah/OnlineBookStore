package org.smart.bookstore.services;

import org.smart.bookstore.data.repositories.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    
    Optional<Book> findOneById(long id);
    
    List<Book> getAll();

    Optional<Book> save(Book t);
    
    Optional<Integer> delete(int t);
}