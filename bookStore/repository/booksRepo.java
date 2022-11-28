package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.bookRented;
import com.bookStore.bookStore.model.books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface booksRepo extends JpaRepository<books, Integer> {
    books findByBookName(String BookName);
    List<books> findAllByBookName(String BookName);
    List<books> findAllByOrderByLikes();
    List<books> findAllByOrderByAddedDate();

}

