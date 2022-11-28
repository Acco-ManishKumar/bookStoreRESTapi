package com.bookStore.bookStore.service;

import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.reviews;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface reviewService {
    reviews addReview(reviews reviews1);
    books likeBook(String bookName, String userName);
    List<reviews> getAllReviews();
    List<reviews> getAllReviewsOfBook(String bookName);
    reviews reportReview(String bookName, String userName);
}
