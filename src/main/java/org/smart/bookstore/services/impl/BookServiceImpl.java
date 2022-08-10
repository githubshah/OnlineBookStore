package org.smart.bookstore.services.impl;

import org.smart.bookstore.data.repositories.BookRepository;
import org.smart.bookstore.data.repositories.DiscountRepository;
import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.data.repositories.entities.BookType;
import org.smart.bookstore.data.repositories.entities.Discount;
import org.smart.bookstore.model.Cart;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findOneById(Integer id) {
        return Optional.of(bookRepository.getOne(id));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>();
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Integer> delete(int id) {
        bookRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Cart checkout(List<Object> books, Optional<Integer> promoCode) {
        List<Integer> bookIds = books.stream()
                .map(x -> x instanceof LinkedHashMap ? ((Integer) ((LinkedHashMap<?, ?>) x).get("isbn")) : ((Integer) x))
                .collect(Collectors.toList());
        return checkoutBookIds(bookIds, promoCode);
    }

    private Cart checkoutBookIds(List<Integer> booksIds, Optional<Integer> promoCode) {
        Cart cart = new Cart();

        /**
         * Calculate sum group by Book category.
         */
        Map<BookType, Double> collect = booksIds
                .stream()
                .map(this::findOneById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(x -> cart.addBook(new Book(x.getISBN(), x.getName(), x.getDescription(), x.getAuthor(), x.getType(), x.getPrice())))
                .collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));

        double[] total = {0.0};

        /**
         * Apply Discount as per Book category
         */
        collect.keySet()
                .forEach(discountType -> {
                    discountRepository.getOneByDiscountTypeAndActiveTrue(discountType)
                            .ifPresent(oneByDiscountType -> {
                                total[0] += percentageOf(oneByDiscountType.getValue(), collect.get(discountType));
                                cart.addMessage(String.format("%s percent of %s = %s", oneByDiscountType, collect.get(discountType), total[0]));
                            });
                });

        /**
         * Apply Promo Code as per given in HttpRequest.
         */
        if (promoCode != null && promoCode.isPresent()) {
            Integer promoCodeValue = promoCode.get();
            int flatDiscount = promoCodeValue < 100 ? 100 : 200;
            double beforeFlat = total[0];
            total[0] = total[0] - flatDiscount;
            cart.addMessage(String.format("%s flat discount on amount %s = %s", flatDiscount, beforeFlat, total[0]));
        }

        cart.setTotal(Optional.of(total[0]));
        return cart;
    }

    private double percentageOf(int percent, Double sum) {
        return (sum * percent) / 100.0;
    }

    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
}