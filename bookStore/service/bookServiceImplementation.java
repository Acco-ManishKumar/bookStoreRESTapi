package com.bookStore.bookStore.service;

import com.bookStore.bookStore.exceptions.customException;
import com.bookStore.bookStore.model.*;
import com.bookStore.bookStore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class bookServiceImplementation implements  bookService{
    @Autowired
    private booksRepo booksRepo1;

    @Autowired
    private inventoryRecordRepo inventoryRecordRepo1;
    @Autowired
    private bookRentedRepo bookRentedRepo1;
    @Autowired
    private walletTransactionRepo walletTransactionRepo1;
    @Autowired
    private userRepo userRepo1;

    @Override
    public List<books> getBooks(){
        return booksRepo1.findAll();
    }

    @Override
    @Transactional
    public List<books> addBook(books book1){
        booksRepo1.save(book1);
        for(int i=1; i<=book1.getAvailableInventory(); i++){
            inventoryRecord record = new inventoryRecord();
            record.setBookName(book1.getBookName());
            record.setAuthor(book1.getAuthor());
            record.setInventoryCode(book1.getBookName()+i);
            inventoryRecordRepo1.save(record);
        }
        return booksRepo1.findAll();
    }

    @Override
    public List<inventoryRecord> getInventory(){
        return inventoryRecordRepo1.findAll();
    }

    @Override
    public List<books> searchBooksByLikes(){
        List<books> book = booksRepo1.findAllByOrderByLikes();
        //book.sort(Comparator.comparing(books::getLikes).reversed());
        return book;
    }
    @Override
    public List<books> searchBooksByAddedDates(){
        List<books> book = booksRepo1.findAllByOrderByAddedDate();
        //book.sort(Comparator.comparing(books::getAddedDate).reversed());
        return book;
    }

    @Override
    @Transactional
    public bookRented borrowBook(String userName, String bookName) {
        Optional<user> userObj = Optional.ofNullable(userRepo1.findByUserName(userName));
        Optional<books> bookObj = Optional.ofNullable(booksRepo1.findByBookName(bookName));
        if (userObj.isPresent()) {
            user user1 = userObj.get();
            if (Objects.equals(user1.getStatus(), "Active")) {
                if (user1.getNoOfBooksRented() < 3) {
                    if (bookObj.isPresent()) {
                        books book = bookObj.get();
                        long currentBookCount = book.getAvailableInventory();
                        if (currentBookCount > 1) {
                            Double price = book.getPrice();
                            Double walletAmount = user1.getWallet();
                            if (checkWalletStatus(price, walletAmount)) {
                                List<bookRented> bookRentedList = bookRentedRepo1.findAllByUserNameAndStatus(user1.getUserName(), "Active");
                                for (bookRented bookRecord : bookRentedList) {
                                    if (Objects.equals(bookRecord.getBookName(), bookName)) {
                                        throw new customException("Book which is requested is already issued to user :" + userName);
                                    }
                                }
                                bookRented bookRecord = new bookRented();
                                bookRecord.setUserId( user1.getUserId());
                                bookRecord.setBookUniqueId(book.getBookName()+book.getAvailableInventory());
                                bookRecord.setUserName(user1.getUserName());
                                bookRecord.setBookId(book.getBookId());
                                bookRecord.setBookName(bookName);
                                bookRecord.setAuthor(book.getAuthor());
                                bookRecord.setStatus("Active");
                                Double addToWallet =user1.getWallet()-  price * 0.2;
                                user1.setWallet(addToWallet);
                                walletTransaction newWallet = new walletTransaction();
                                newWallet.setUserId(user1.getUserId());
                                newWallet.setTransaction("-"+(price*0.2));
                                walletTransactionRepo1.save(newWallet);
                                Integer updatedNoOfBooksRented = user1.getNoOfBooksRented() +1;
                                user1.setNoOfBooksRented(updatedNoOfBooksRented);
                                userRepo1.save(user1);
                                inventoryRecord inventoryRecord1 = inventoryRecordRepo1.findByInventoryCode(book.getBookName()+book.getAvailableInventory());
                                inventoryRecord1.setIssuedTo(user1.getUserId());
                                inventoryRecordRepo1.save(inventoryRecord1);
                                Integer updatedNoOfBooks = book.getAvailableInventory() -1;
                                book.setAvailableInventory(updatedNoOfBooks);
                                return bookRentedRepo1.save(bookRecord);
                            }else {
                                throw new customException("wallet have insufficient balance, wallet balance:" + user1.getWallet() + "book security deposit : " + (book.getPrice() * 0.2));
                            }
                            } else {
                                throw new customException("Book : " + bookName + " is unavailable at present");
                            }
                        } else {
                            throw new customException("Book not available with provided book name:" + bookName);
                        }
                    } else {
                        throw new customException("user :" + userName + "had already 3 books rented");
                    }
                } else {
                    throw new customException("user :" + userName + "had been suspended");
                }
            } else {
                throw new customException("User not available with provided user name :" + userName);
            }
        }
    @Override
    @Transactional
    public bookRented returnBook(String userName, String bookName){
        Optional<bookRented> rentedBookObj = Optional.ofNullable(bookRentedRepo1.findOneByUserNameAndBookNameAndStatus(userName, bookName, "Active"));
        if(rentedBookObj.isPresent()){
            bookRented rentedBook1 =rentedBookObj.get();
            books book = booksRepo1.findByBookName(bookName);
            user user1 = userRepo1.findByUserName(userName);
            Double price = book.getPrice();
            Double addToWallet =user1.getWallet()+  price * 0.1;
            user1.setWallet(addToWallet);
            walletTransaction newWallet = new walletTransaction();
            newWallet.setUserId(user1.getUserId());
            newWallet.setTransaction("+"+(price*0.1));
            walletTransactionRepo1.save(newWallet);
            userRepo1.save(user1);
            inventoryRecord inventoryRecord1 = inventoryRecordRepo1.findByInventoryCode(rentedBook1.getBookUniqueId());
            inventoryRecord1.setIssuedTo(null);
            inventoryRecordRepo1.save(inventoryRecord1);
            rentedBook1.setStatus("Disabled");
            Integer updatedNoOfBooksRented = user1.getNoOfBooksRented() -1;
            user1.setNoOfBooksRented(updatedNoOfBooksRented);
            Integer updatedNoOfBooks = book.getAvailableInventory() +1;
            book.setAvailableInventory(updatedNoOfBooks);
            return bookRentedRepo1.save(rentedBook1);
        }else{
            throw new customException("book not rented");
        }

    }
    public boolean checkWalletStatus (Double price, Double walletAmount){
        return (price * 0.2) < walletAmount;
    }

}
