package com.ecommerce.api.exception;

public class WishListItemNotExistException extends IllegalArgumentException {
    public WishListItemNotExistException(String msg) {
        super(msg);
    }
}