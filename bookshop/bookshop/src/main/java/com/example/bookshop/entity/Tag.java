//package com.example.bookshop.entity;
//
//import jakarta  .persistence.*;
//
package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Data

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
}
