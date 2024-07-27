package com.example.bookshop.dto;

import com.example.bookshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private User user;
//    private Integer userId;
    private Date orderDate;
    private String status;
    private Integer total;
    private Integer shippingFee;
    private Integer discount;
}
