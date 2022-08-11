package org.smart.bookstore.controllers;

import org.smart.bookstore.data.entities.Book;
import org.smart.bookstore.data.entities.Discount;
import org.smart.bookstore.data.entities.PromoCode;
import org.smart.bookstore.model.Cart;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<List<Book>> getBook(@RequestParam Optional<Integer> id,
                                              @RequestParam Optional<Integer> page,
                                              @RequestParam Optional<Integer> size) {


        return id.map(bookId -> bookService.findOneById(bookId)
                        .map(book -> new ResponseEntity<>(Collections.singletonList(book), HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity<>(bookService.getAll(PageRequest.of(page.orElse(0), size.orElse(10))), HttpStatus.OK));
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

    @PostMapping(path = "checkout")
    public ResponseEntity<Cart> checkout(@RequestBody List<Object> books, @RequestParam("promoCode") Optional<String> promoCode) {
        return new ResponseEntity<>(bookService.checkout(books, promoCode), HttpStatus.OK);
    }

    @PostMapping(path = "discount")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        return new ResponseEntity<>(bookService.saveDiscount(discount), HttpStatus.OK);
    }

    @PostMapping(path = "promocode")
    public ResponseEntity<PromoCode> createPromoCode(@RequestBody PromoCode promoCode) {
        return new ResponseEntity<>(bookService.savePromoCode(promoCode), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return bookService.save(book).map(persistBook -> new ResponseEntity<>(persistBook, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
