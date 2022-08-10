package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookBookService;


    @GetMapping(path="/", produces = "application/json")
    public Book main(){

        Book book = new Book();
        book.setAuthor("shaid");
        book.setDescription("good");
        book.setName("shaidhussain");
        book.setType("hoo");

        //bookBookService.save(book);
        return book;
    }
}
