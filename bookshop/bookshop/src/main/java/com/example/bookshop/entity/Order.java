//package com.example.bookshop.entity;
//import jakarta.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "Orders")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column
//    private LocalDateTime orderDate;
//
//    @Column
//    private String status;
//
//    @Column(nullable = false)
//    private BigDecimal total;
//
//    @Column
//    private BigDecimal shippingFee;
//
//    @Column
//    private Float discount;
//
//    // Getters and Setters
//}
