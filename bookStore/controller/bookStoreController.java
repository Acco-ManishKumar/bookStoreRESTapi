package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.model.*;

import com.bookStore.bookStore.service.bookService;
import com.bookStore.bookStore.service.reviewService;
import com.bookStore.bookStore.service.transactionService;
import com.bookStore.bookStore.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/bookstore")
public class bookStoreController {
    @Autowired
    private bookService bookService1;
    @Autowired
    private userService userService1;
    @Autowired
    private reviewService reviewService1;
    @Autowired
    private transactionService transactionService1;

    @PostMapping(path = "/createUser")
    private ResponseEntity<user> createUser(@RequestBody user user1) {
        return ResponseEntity.ok().body(this.userService1.createUser(user1));
    }
    @GetMapping(path = "/users")
    private ResponseEntity<List<user>> getUsers(){
        return ResponseEntity.ok().body(this.userService1.getUsers());
    }

    @PutMapping(path = "/updateUser")
    private ResponseEntity<user> updateUser(@RequestBody user user1) {
        return ResponseEntity.ok().body(this.userService1.updateUser(user1));
    }

    @PostMapping(path = "/addMoneyToWallet/{userId}/{amount}")
    private ResponseEntity<user> addMoneyToWallet(@PathVariable Integer userId, @PathVariable Double amount) {
        return ResponseEntity.ok().body(this.userService1.addMoneyToWallet(userId,amount));
    }

    @PostMapping(path = "/suspendUser/{userId}")
    private ResponseEntity<user> suspendUser(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(this.userService1.suspendUser(userId));
    }

    @PostMapping(path = "/addBook")
    private ResponseEntity<List<books>> addBook(@RequestBody books book) {
        return ResponseEntity.ok().body(this.bookService1.addBook(book));
    }
    @GetMapping(path = "/books")
    private ResponseEntity<List<books>> getBooks(){
        return ResponseEntity.ok().body(this.bookService1.getBooks());
    }

    @GetMapping(path = "/getInventory")
    private ResponseEntity<List<inventoryRecord>> getInventory(){
        return ResponseEntity.ok().body(this.bookService1.getInventory());
    }

    @GetMapping(path = "/searchBooksByLikes")
    private ResponseEntity<List<books>> searchBooksByLikes(){
        return ResponseEntity.ok().body(this.bookService1.searchBooksByLikes());
    }

    @GetMapping(path = "/searchBooksByAddedDates")
    private ResponseEntity<List<books>> searchBooksByAddedDate(){
        return ResponseEntity.ok().body(this.bookService1.searchBooksByAddedDates());
    }

    @PostMapping(path = "/borrowBook/{userName}/{bookName}")
    private ResponseEntity<bookRented> borrowBook(@PathVariable String userName, @PathVariable String bookName) {
        return ResponseEntity.ok().body(this.bookService1.borrowBook(userName,bookName));
    }

    @PostMapping(path = "/returnBook/{userName}/{bookName}")
    private ResponseEntity<bookRented> returnBook(@PathVariable String userName, @PathVariable String bookName) {
        return ResponseEntity.ok().body(this.bookService1.returnBook(userName,bookName));
    }

    @GetMapping(path = "/walletHistory/{userId}")
    private ResponseEntity<List<walletTransaction>> getUserWalletHistory(@PathVariable Integer userId){
        return ResponseEntity.ok().body(this.transactionService1.getUserWalletTransactionHistory(userId));
    }

//    @GetMapping(path = "/rentalHistory/{userId}")
//    private ResponseEntity<List<bookRented>> getUserRentalHistory(@PathVariable Integer userId){
//        return ResponseEntity.ok().body(this.transactionService1.userRentalTransactionHistory(userId));
//    }

    @GetMapping(path = "/rentalHistory/{userName}")
    private ResponseEntity<List<bookRented>> getUserRentalHistory(@PathVariable String userName){
        return ResponseEntity.ok().body(this.transactionService1.userRentalTransactionHistory(userName));
    }

    @GetMapping(path = "/storeRentalHistory")
    private ResponseEntity<List<bookRented>> getStoreRentalHistory(){
        return ResponseEntity.ok().body(this.transactionService1.getAllRentalHistory());
    }

        @GetMapping(path = "/getAllReviews")
    private ResponseEntity<List<reviews>> getAllReviews(){
        return ResponseEntity.ok().body(this.reviewService1.getAllReviews());
    }

    @GetMapping(path = "/getAllReviewsOfBook/{bookName}")
    private ResponseEntity<List<reviews>> getAllReviewsOfBook(@PathVariable String bookName){
        return ResponseEntity.ok().body(this.reviewService1.getAllReviewsOfBook(bookName));
    }

    @PostMapping(path = "/addReview")
    private ResponseEntity<reviews> addReview(@RequestBody reviews reviews1) {
        return ResponseEntity.ok().body(this.reviewService1.addReview(reviews1));
    }
    @PostMapping(path = "/likeBook/{bookName}/{userName}")
    private ResponseEntity<books> likeBook(@PathVariable String bookName, @PathVariable String userName) {
        return ResponseEntity.ok().body(this.reviewService1.likeBook(bookName,userName));
    }

        @PostMapping(path = "/reportReview/{bookName}/{userName}")
    private ResponseEntity<reviews> reportReview(@PathVariable String bookName, @PathVariable String userName) {
        return ResponseEntity.ok().body(this.reviewService1.reportReview(bookName,userName));
    }

}
