package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    private BookService bookBookService;

    @GetMapping(path = "")
    public ResponseEntity<List<Book>> getBook(@RequestParam Optional<Long> id) {
        return id.map(bookId -> bookBookService.findOneById(bookId)
                        .map(book -> new ResponseEntity<>(Collections.singletonList(book), HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity<>(bookBookService.getAll(), HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookVaiPath(@PathVariable("id") Long id) {
        return bookBookService.findOneById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Book> deleteBookViaPath(@PathVariable("id") long id) {
        return bookBookService.delete(id).map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
