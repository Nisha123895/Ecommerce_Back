package com.example.demo.service;

import java.util.List;

import com.example.demo.Exception.productException;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.request.ReviewRequest;


public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws productException;

    public List<Review> getAllReview(Long productId);


}
