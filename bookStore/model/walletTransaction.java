package com.bookStore.bookStore.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "walletTransaction")
public class walletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "transaction")
    private String transaction;
    @CreationTimestamp
    private Date transactionDate;
}
