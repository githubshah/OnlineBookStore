package org.smart.bookstore.services;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.data.repositories.entities.Discount;
import org.smart.bookstore.data.repositories.entities.PromoCode;
import org.smart.bookstore.model.Cart;

import java.util.List;
import java.util.Optional;

public interface BookService {
    
    Optional<Book> findOneById(Integer id);
    
    List<Book> getAll();

    Optional<Book> save(Book t);
    
    Optional<Integer> delete(int t);

    Cart checkout(List<Object> books, Optional<Integer> promoCode);

    Discount saveDiscount(Discount discount);

    PromoCode savePromoCode(PromoCode promoCode);
}