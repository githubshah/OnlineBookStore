package org.smart.bookstore.data.repositories.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "promocodes")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(unique = true)
    private String code;
    private int flatDiscount;
    private int discountApplicableAmount;
    private boolean active;

    public PromoCode() {
    }

    public PromoCode(String code, int flatDiscount, int discountApplicableAmount, boolean active) {
        this.code = code;
        this.flatDiscount = flatDiscount;
        this.discountApplicableAmount = discountApplicableAmount;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public int getFlatDiscount() {
        return flatDiscount;
    }

    public int getDiscountApplicableAmount() {
        return discountApplicableAmount;
    }

    public boolean isActive() {
        return active;
    }
}
