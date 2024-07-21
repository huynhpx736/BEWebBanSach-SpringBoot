package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String biography;
}
