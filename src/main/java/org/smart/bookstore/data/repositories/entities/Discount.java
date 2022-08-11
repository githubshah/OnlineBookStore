package org.smart.bookstore.data.repositories.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private BookType discountType;
    private int value;
    private boolean active;

    public Discount() {
    }

    public Discount(BookType discountType, int value, boolean active) {
        this.discountType = discountType;
        this.value = value;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(BookType discountType) {
        this.discountType = discountType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
