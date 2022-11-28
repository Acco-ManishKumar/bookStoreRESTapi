package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<user, Integer> {
    user findByUserName(String UserName);
}
