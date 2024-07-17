//package com.example.bookshop.entity;
//
//import jakarta.persistence.*;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "Products")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long productId;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column
//    private String description;
//
//    @Column(nullable = false)
//    private BigDecimal price;
//
//    @Column
//    private Integer publicationYear;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "publisher_id")
//    private Publisher publisher;
//
//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    private Author author;
//
//    @Column
//    private Integer salesVolume;
//
//    @Column
//    private Float starRating;
//
//    @Column
//    private String image;
//
//    // Getters and Setters
//}
//
