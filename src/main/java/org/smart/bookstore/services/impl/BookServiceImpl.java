package org.smart.bookstore.services.impl;

import org.smart.bookstore.data.repositories.BookRepository;
import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService<Book> {


    private static List<Book> list = new ArrayList();

    static {
        list.add(new Book(11, "name_1", "desc_1", "auth_1", "fiction", 100));
        list.add(new Book(12, "name_2", "desc_2", "auth_2", "fiction", 100));
        list.add(new Book(13, "name_3", "desc_3", "auth_3", "fiction", 100));
        list.add(new Book(14, "name_4", "desc_4", "auth_4", "comics", 100));
        list.add(new Book(15, "name_5", "desc_5", "auth_5", "comics", 100));
        list.add(new Book(16, "name_6", "desc_6", "auth_6", "comics", 100));
        list.add(new Book(17, "name_7", "desc_7", "auth_7", "comics", 100));
    }

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> get(long id) {
        return list.stream().filter(x -> x.getISBN() == id).findFirst();
    }

    @Override
    public List<Book> getAll() {
        return list;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book, String[] params) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public boolean delete(long id) {
        return list.stream().filter(x -> x.getISBN() == id).findFirst().isPresent();
    }
}