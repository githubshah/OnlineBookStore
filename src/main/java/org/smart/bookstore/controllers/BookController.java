package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    private BookService bookBookService;

    @GetMapping(path = "/")
    public List<Book> getBook() {
        return bookBookService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookByPath(@PathVariable("id") Long id) {
        Optional<Book> optional = bookBookService.get(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = optional.get();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public java.util.Optional getBookByParam(@RequestParam Long id) {
        return bookBookService.get(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> deleteBookByPath(@PathVariable("id") long id) {
        boolean isRemoved = bookBookService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
