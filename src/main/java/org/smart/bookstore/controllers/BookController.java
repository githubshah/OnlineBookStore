package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.model.Cart;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "")
    public ResponseEntity<List<Book>> getBook(@RequestParam Optional<Integer> id) {
        return id.map(bookId -> bookService.findOneById(bookId)
                        .map(book -> new ResponseEntity<>(Collections.singletonList(book), HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity<>(bookService.getAll(), HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookVaiPath(@PathVariable("id") Integer id) {
        return bookService.findOneById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> deleteBookViaPath(@PathVariable("id") int id) {
        return bookService.delete(id).map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<Book> deleteBookViaPath(@RequestBody Book book) {
        return bookService.save(book).map(persistBook -> new ResponseEntity<>(persistBook, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "checkout")
    public Cart checkout(@RequestBody Cart cart) {
        ///return new ResponseEntity<>(bookService.checkout(cart), HttpStatus.OK);
        return bookService.checkout(cart);

    }
}
