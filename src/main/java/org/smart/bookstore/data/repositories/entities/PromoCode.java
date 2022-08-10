package org.smart.bookstore.data.repositories.entities;

import javax.persistence.*;

@Entity
@Table(name = "promocodes")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int code;
    private int flatDiscount;
    private int discountApplicableAmount;
    private boolean active;

    public PromoCode() {
    }

    public PromoCode(int code, int flatDiscount, int discountApplicableAmount, boolean active) {
        this.code = code;
        this.flatDiscount = flatDiscount;
        this.discountApplicableAmount = discountApplicableAmount;
        this.active = active;
    }

    public int getCode() {
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
