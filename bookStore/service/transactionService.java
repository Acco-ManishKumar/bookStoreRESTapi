package com.bookStore.bookStore.service;

import com.bookStore.bookStore.model.bookRented;
import com.bookStore.bookStore.model.user;
import com.bookStore.bookStore.model.walletTransaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface transactionService {
    List<bookRented> getAllRentalHistory();
    List<bookRented> userRentalTransactionHistory(String userName);

    List<walletTransaction> getUserWalletTransactionHistory(Integer userId);

}
