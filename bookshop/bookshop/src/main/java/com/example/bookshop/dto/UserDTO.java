package com.example.bookshop.dto;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Integer role;
    private String avatar;
    private String fullname;
    private String phoneNumber;
    private String classification;
}

