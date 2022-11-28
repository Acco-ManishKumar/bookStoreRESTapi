package com.bookStore.bookStore.service;

import com.bookStore.bookStore.model.bookRented;
import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.inventoryRecord;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface bookService{
    List<books>getBooks();
    List<books> addBook(books book1);

    List<inventoryRecord> getInventory();

    List<books> searchBooksByLikes();
    List<books> searchBooksByAddedDates();
    bookRented borrowBook(String userName, String bookName);
    bookRented returnBook(String userName, String bookName);
}
