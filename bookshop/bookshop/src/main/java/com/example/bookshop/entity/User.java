//package com.example.bookshop.entity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//
package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private int role;
    private String avatar;
    private String fullname;
    private String phoneNumber;
    private String classification;
    private String phone;
}
