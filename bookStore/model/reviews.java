package com.bookStore.bookStore.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewId;
    @Column(name = "bookName")
    private String  bookName;
    @Column(name = "reviewText")
    private String  reviewText;
    @Column(name = "userName")
    private String  userName;
    @Column(name = "strikes")
    private long strikes;
    @Column(name = "status")
    private String status;
    @CreationTimestamp
    private Date reviewDate;

}
