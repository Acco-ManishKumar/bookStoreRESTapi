package com.bookStore.bookStore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventoryRecord")
public class inventoryRecord {
    @Id
    @Column(name = "InventoryCode", unique=true)
    private String  inventoryCode;
    @Column(name = "bookName")
    private String  bookName;
    @Column(name = "author")
    private String  author;
    @Column(name = "issuedTo")
    private  Integer issuedTo;

}
