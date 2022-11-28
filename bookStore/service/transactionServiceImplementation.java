package com.bookStore.bookStore.service;

import com.bookStore.bookStore.model.bookRented;
import com.bookStore.bookStore.model.walletTransaction;
import com.bookStore.bookStore.repository.bookRentedRepo;
import com.bookStore.bookStore.repository.walletTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class transactionServiceImplementation implements transactionService {

    @Autowired
    private walletTransactionRepo walletTransactionRepo1;

    @Autowired
    private bookRentedRepo bookRentedRepo1;


    @Override
    public List<bookRented> userRentalTransactionHistory(String userName){
        return bookRentedRepo1.findAllByUserName(userName);
    }

    @Override
    public List<bookRented> getAllRentalHistory(){
        return bookRentedRepo1.findAll();
    }

    @Override
    public List<walletTransaction> getUserWalletTransactionHistory(Integer userId){
        return walletTransactionRepo1.findByUserId(userId);
    }
}
