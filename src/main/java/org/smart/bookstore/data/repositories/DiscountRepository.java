package org.smart.bookstore.data.repositories;

import org.smart.bookstore.data.entities.BookType;
import org.smart.bookstore.data.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    Optional<Discount> getOneByDiscountTypeAndActiveTrue(BookType discountType);
}