package com.example.demo.service;


import com.example.demo.Exception.productException;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.User;
import com.example.demo.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);

    public CartItem addCartItem(Long userId, AddItemRequest req) throws productException;

    public Cart findUserCart(Long userId);

}
