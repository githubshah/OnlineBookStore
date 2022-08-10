package org.smart.bookstore.services.impl;

import org.smart.bookstore.data.repositories.BookRepository;
import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.model.Cart;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


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
    public Optional<Book> findOneById(Integer id) {
        return Optional.of(bookRepository.getOne(id));
    }

    @Override
    public List<Book> getAll() {
        return list;
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
    public Cart checkoutBook(List<Book> books, Optional<Integer> promoCode) {
        List<Integer> booksIds = books.stream().map(Book::getISBN).collect(Collectors.toList());
        return checkoutBookIds(booksIds, promoCode);
    }

    @Override
    public Cart checkoutBookIds(List<Integer> booksIds, Optional<Integer> promoCode) {
        Cart cart = new Cart();

        Map<String, Double> collect = booksIds
                .stream()
                .map(this::findOneById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(x -> cart.addBook(new Book(x.getISBN(), x.getName(), x.getDescription(), x.getAuthor(), x.getType(), x.getPrice())))
                .collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));

        double[] total = {0.0};

        collect.keySet()
                .forEach(q1 -> {
                    if ("fiction".equals(q1)) {
                        total[0] += percentageOf(20, collect.get(q1));
                        cart.addMessage(String.format("%s percent of %s = %s", 20, collect.get(q1), total[0]));
                    }
                });

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
}