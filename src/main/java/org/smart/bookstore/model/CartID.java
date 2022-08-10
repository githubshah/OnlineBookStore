package org.smart.bookstore.model;

import java.util.List;

public class CartID extends Cart {
    List<Integer> booksIds;

    public List<Integer> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(List<Integer> booksIds) {
        this.booksIds = booksIds;
    }
}
