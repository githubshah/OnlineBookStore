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

    @GetMapping(path = "")
    public ResponseEntity<Book> getBookByParam(@RequestParam Long id) {
        return bookBookService.findOneById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookByPath(@PathVariable("id") Long id) {
        return bookBookService.findOneById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
