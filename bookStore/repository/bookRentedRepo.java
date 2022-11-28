package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.bookRented;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface bookRentedRepo extends JpaRepository<bookRented, Integer> {
   List<bookRented> findAllByUserNameAndStatus(String UserName, String Status);
   List<bookRented> findAllByUserName(String userName);
   bookRented findOneByUserNameAndBookName(String UserName, String BookName);
   bookRented findOneByUserNameAndBookNameAndStatus(String UserName, String BookName, String Status);
}
