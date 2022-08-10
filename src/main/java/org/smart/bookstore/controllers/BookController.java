package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookBookService;

    @GetMapping(path = "/as", produces = "application/json")
    public Book main() {

//        Book book = new Book();
//        book.setAuthor("shaid");
//        book.setDescription("good");
//        book.setName("shaidhussain");
//        book.setType("hoo");

        //bookBookService.save(book);
        return null;
    }

    @GetMapping(path = "/")
    public List<Book> getBook() {
        return bookBookService.getAll();
    }
}
