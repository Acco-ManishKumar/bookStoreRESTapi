package com.bookStore.bookStore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UserId;
    @Column(name = "userName", unique=true)
    private String  userName;
    @Column(name = "userEmail", unique=true)
    private String  userEmail;
    @Column(name = "userMobile", unique=true)
    private long userMobile;
    @Column(name = "wallet")
    private Double wallet;
    @Column(name = "strikes")
    private Integer strikes;
    @Column(name = "status")
    private String status;
    @Column(name = "noOfBooksRented")
    private Integer noOfBooksRented;
}