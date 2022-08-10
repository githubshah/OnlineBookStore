package org.smart.bookstore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.smart.bookstore.data.repositories.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public
class Cart {
    Optional<Integer> promoCode;
    Optional<Double> total;
    Optional<List<String>> message;

    public Optional<Integer> getPromoCode() {
        return promoCode;
    }

    public Optional<Double> getTotal() {
        return total;
    }

    public void setTotal(Optional<Double> total) {
        this.total = total;
    }

    synchronized public void addMessage(String info) {
        if (message == null) {
            this.message = Optional.of(new ArrayList<>());
        }
        message.get().add(info);
    }

    public Optional<List<String>> getMessage() {
        return message;
    }

    List<Book> books;

    synchronized public void addBook(Book info) {
        if (books == null) {
            this.books = new ArrayList<>();
        }
        books.add(info);
    }

    public List<Book> getBooks() {
        return books;
    }
}
