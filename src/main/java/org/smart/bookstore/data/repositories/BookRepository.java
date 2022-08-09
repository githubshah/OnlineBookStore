package org.smart.bookstore.data.repositories;

import org.smart.bookstore.data.repositories.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}