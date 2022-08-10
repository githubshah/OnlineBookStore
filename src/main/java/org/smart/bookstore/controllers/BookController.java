package org.smart.bookstore.controllers;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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
    public java.util.Optional getBookByPath(@PathVariable("id") Long id) {
        return bookBookService.get(id);
    }

    @GetMapping(path = "")
    public java.util.Optional getBookByParam(@RequestParam Long id) {
        return bookBookService.get(id);
    }


}
