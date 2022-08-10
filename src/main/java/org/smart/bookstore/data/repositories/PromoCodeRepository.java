package org.smart.bookstore.data.repositories;

import org.smart.bookstore.data.repositories.entities.Book;
import org.smart.bookstore.data.repositories.entities.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {

    Optional<PromoCode> findByCode(Integer promoCodeValue);
}