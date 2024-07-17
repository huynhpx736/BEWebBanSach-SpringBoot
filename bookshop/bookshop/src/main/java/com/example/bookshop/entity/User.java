//package com.example.bookshop.entity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//
//@Entity
//@Table(name = "Users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column
//    private Integer role;
//
//    @Column
//    private String avatar;
//
//    @Column
//    private String fullname;
//
//    @Column
//    private String phoneNumber;
//
//    @Column
//    private String classification;
//
//    // Getters and Setters
//}
//
//
//// Tương tự cho các entity khác: Product, Category, Publisher, Author, Order, OrderDetail, Review, Tag
