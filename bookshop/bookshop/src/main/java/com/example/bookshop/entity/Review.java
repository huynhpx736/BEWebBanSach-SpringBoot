//package com.example.bookshop.entity;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "Reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(nullable = false)
//    private Integer rating;
//
//    @Column
//    private String comment;
//
//    // Getters and Setters
//}
