package org.smart.bookstore.services;

import org.smart.bookstore.data.entities.Discount;
import org.smart.bookstore.data.entities.PromoCode;
import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.model.Cart;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findOneById(Integer id);

    List<Book> getAll(PageRequest of);

    Optional<Book> save(Book t);

    Optional<Integer> delete(int t);

    Cart checkout(List<Object> books, Optional<String> promoCode);

    Discount saveDiscount(Discount discount);

    PromoCode savePromoCode(PromoCode promoCode);
}