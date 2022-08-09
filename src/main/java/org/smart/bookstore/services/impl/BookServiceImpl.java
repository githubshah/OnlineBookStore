package org.smart.bookstore.services.impl;

import org.smart.bookstore.dto.BookDto;
import org.smart.bookstore.services.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService<BookDto> {

    @Override
    public Optional<BookDto> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<BookDto> getAll() {
        return null;
    }

    @Override
    public void save(BookDto book) {

    }

    @Override
    public void update(BookDto book, String[] params) {

    }

    @Override
    public void delete(BookDto book) {

    }
}