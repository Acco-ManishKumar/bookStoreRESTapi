package com.bookStore.bookStore.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bookRented")
public class bookRented {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer BorrowId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "userName")
    private String  userName;
    @Column(name = "bookId")
    private Integer  bookId;
    @Column(name = "bookUniqueId")
    private String  bookUniqueId;
    @Column(name = "bookName")
    private String  bookName;
    @Column(name = "author")
    private String  author;
    @Column(name = "status")
    private String status;
    @CreationTimestamp
    private Date borrowDate;
    @UpdateTimestamp
    private  Date returnDate;
}
