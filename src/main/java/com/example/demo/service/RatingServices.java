package com.example.demo.service;

import java.util.List;

import com.example.demo.Exception.productException;
import com.example.demo.model.Rating;
import com.example.demo.model.User;

import com.zosh.request.RatingRequest;

public interface RatingServices {

    public Rating createRating(RatingRequest req, User user) throws productException;

    public List<Rating> getProductsRating(Long productId);

}
