//package com.example.bookshop.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "Categories")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = false, unique = true)
//    private String name;
//
//}
//
package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
