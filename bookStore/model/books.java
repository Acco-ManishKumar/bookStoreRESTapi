package com.bookStore.bookStore.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "books")
public class books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Column(name = "bookName", unique=true)
    private String  bookName;
    @Column(name = "author")
    private String  author;
    @Column(name = "availableInventory" ,nullable=false)
    private Integer availableInventory;
    @Column(name = "Price")
    private Double price;
    @Column(name = "Category")
    private String category;
    @Column(name = "likes")
    private  Integer likes;
    @CreationTimestamp
    private Date addedDate;
}
