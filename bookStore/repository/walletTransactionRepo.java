package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.walletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface walletTransactionRepo extends JpaRepository<walletTransaction, Integer> {
    List<walletTransaction> findByUserId(Integer userId);
}
