package org.smart.bookstore.data.repositories;

import org.smart.bookstore.data.repositories.entities.BookType;
import org.smart.bookstore.data.repositories.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    Discount getOneByDiscountType(BookType discountType);
}