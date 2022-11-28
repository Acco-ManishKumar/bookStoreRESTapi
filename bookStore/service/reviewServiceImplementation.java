package com.bookStore.bookStore.service;

import com.bookStore.bookStore.exceptions.customException;
import com.bookStore.bookStore.model.bookRented;
import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.reviews;
import com.bookStore.bookStore.model.user;
import com.bookStore.bookStore.repository.bookRentedRepo;
import com.bookStore.bookStore.repository.booksRepo;
import com.bookStore.bookStore.repository.reviewsRepo;
import com.bookStore.bookStore.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reviewServiceImplementation implements reviewService {
    @Autowired
    private reviewsRepo reviewsRepo1;
    @Autowired
    private userRepo userRepo1;
    @Autowired
    private bookRentedRepo bookRentedRepo1;

    @Autowired
    private booksRepo  booksRepo1;

    @Override
    public reviews addReview(reviews reviews1){

        Optional<bookRented> isRentedBookObj = Optional.ofNullable(bookRentedRepo1.findOneByUserNameAndBookName(reviews1.getUserName(), reviews1.getBookName()));
        if(isRentedBookObj.isPresent()){
            return reviewsRepo1.save(reviews1);
        }
        else{
            throw new customException("user doent have this book");
        }

    }

    @Override
    public books likeBook(String bookName, String userName){
        books book = booksRepo1.findByBookName(bookName);
        book.setLikes(book.getLikes()+1);
        booksRepo1.save(book);
        return book;
    }

    @Override
    public List<reviews> getAllReviews(){
        return reviewsRepo1.findAllByStatus("Active");
    }
    @Override
    public List<reviews> getAllReviewsOfBook(String bookName){
        return reviewsRepo1.findAllByBookNameAndStatus(bookName, "Active");
    }


    @Override
    public reviews reportReview(String bookName, String userName){
        Optional<reviews> reviewObj = Optional.ofNullable(reviewsRepo1.findOneByBookNameAndUserName(bookName, userName));
        if(reviewObj.isPresent()){
            reviews review = reviewObj.get();
            review.setStrikes(review.getStrikes()+1);
            if(review.getStrikes() == 3){
                review.setStatus("Disabled");
                user user1 = userRepo1.findByUserName(userName);
                user1.setStatus("Suspended");
            }
            return review;
        }
        else {
            throw new customException("Review does not exist on book"+bookName+" by user "+userName);
        }
    }
}
