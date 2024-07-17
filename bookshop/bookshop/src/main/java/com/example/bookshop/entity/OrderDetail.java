//package com.example.bookshop.entity;
//
//import jakarta.persistence.*;
//
//
//@Entity
//@Table(name = "OrderDetails")
//public class OrderDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(nullable = false)
//    private Integer quantity;
//
//    @Column(nullable = false)
//    private Integer unitPrice;
//
//    // Getters and Setters
//}
