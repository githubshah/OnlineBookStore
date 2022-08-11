package org.smart.bookstore.data.repositories;

import org.smart.bookstore.data.entities.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {

    Optional<PromoCode> findByCode(String promoCodeValue);
}