//package com.example.bookshop.entity;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "ProductTags")
//public class ProductTag {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "tag_id")
//    private Tag tag;
//
//    // Getters and Setters
//}
