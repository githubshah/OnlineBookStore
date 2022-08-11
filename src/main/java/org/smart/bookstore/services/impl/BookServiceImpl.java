package org.smart.bookstore.services.impl;

import org.smart.bookstore.data.entities.Book;
import org.smart.bookstore.data.entities.BookType;
import org.smart.bookstore.data.entities.Discount;
import org.smart.bookstore.data.entities.PromoCode;
import org.smart.bookstore.data.repositories.BookRepository;
import org.smart.bookstore.data.repositories.DiscountRepository;
import org.smart.bookstore.data.repositories.PromoCodeRepository;
import org.smart.bookstore.model.Cart;
import org.smart.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findOneById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAll(PageRequest of) {
        return bookRepository.findAll(of).getContent();
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public PromoCode savePromoCode(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    @Override
    public Optional<Integer> delete(int id) {
        bookRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Cart checkout(List<Object> books, Optional<String> promoCode) {
        List<Integer> bookIds = books.stream()
                .map(x -> x instanceof LinkedHashMap ? ((Integer) ((LinkedHashMap<?, ?>) x).get("isbn")) : ((Integer) x))
                .collect(Collectors.toList());
        return checkoutBookIds(bookIds, promoCode);
    }

    private Cart checkoutBookIds(List<Integer> booksIds, Optional<String> promoCode) {
        Cart cart = new Cart();
        double[] total = {0.0};

        /**
         * Calculate sum on group-by Book category.
         */
        Map<BookType, Double> collect = booksIds
                .stream()
                .map(this::findOneById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(x -> cart.addBook(new Book(x.getISBN(), x.getName(), x.getDescription(), x.getAuthor(), x.getType(), x.getPrice())))
                .collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));


        /**
         * Apply Discount as per Book category
         */
        collect.keySet()
                .forEach(discountType -> {
                    discountRepository.getOneByDiscountTypeAndActiveTrue(discountType)
                            .ifPresent(discountTypeVar -> {
                                int discountPercentage = discountTypeVar.getValue();
                                double discountAmount = percentageOf(discountPercentage, collect.get(discountType));
                                Double payableAmount = collect.get(discountType);
                                total[0] += (payableAmount - discountAmount);
                                cart.addMessage(String.format("%s percent discount on %s Books of Rs.%s is Rs.%s", discountPercentage, discountType, payableAmount, discountAmount));
                            });
                });

        /**
         * Apply Promo Code as per given in HttpRequest.
         */
        if (promoCode != null && promoCode.isPresent()) {
            String promoCodeValue = promoCode.get();
            Optional<PromoCode> byCode = promoCodeRepository.findByCode(promoCodeValue);
            if (byCode.isPresent()) {
                PromoCode promoCodeValueVar = byCode.get();
                if (promoCodeValueVar.isActive()) {
                    int flatDiscount = promoCodeValueVar.getFlatDiscount();
                    double beforeFlatDiscount = total[0];
                    if (beforeFlatDiscount >= promoCodeValueVar.getDiscountApplicableAmount()) {
                        total[0] = total[0] - flatDiscount;
                        cart.addMessage(String.format("Flat Rs.%s discount on amount Rs.%s is Rs.%s", flatDiscount, beforeFlatDiscount, total[0]));
                    } else {
                        cart.addMessage(String.format("Flat Rs.%s off on minimum order value Rs.%s", flatDiscount, promoCodeValueVar.getDiscountApplicableAmount()));
                    }
                } else {
                    cart.addMessage(String.format("Promo code %s is not valid or inactive", promoCodeValue));
                }
            }
        }

        cart.setPayableAmount(Optional.of(total[0]));
        return cart;
    }

    private double percentageOf(int percent, Double sum) {
        return (sum * percent) / 100.0;
    }
}