package com.example.bookshop.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String contactEmail;

    // Getters and Setters
}

