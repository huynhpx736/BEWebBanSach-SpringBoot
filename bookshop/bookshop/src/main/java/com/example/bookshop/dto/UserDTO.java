package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private int role;
    private String avatar;
    private String fullname;
    private String phoneNumber;
    private String classification;
    private String phone;
}
