package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reviewsRepo extends JpaRepository<reviews, Long> {
    List<reviews> findAllByBookNameAndStatus(String BookName, String Status);
    List<reviews> findAllByStatus(String Status);
    reviews findOneByBookNameAndUserName(String BookName, String UserName);
}
