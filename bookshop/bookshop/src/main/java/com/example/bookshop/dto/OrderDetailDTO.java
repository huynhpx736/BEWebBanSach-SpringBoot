package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer price;
    private String image;
    private Float weight;
    private String productName;
    private Integer totalPrice;
    private Integer stock;

//    private Date createdAt;
//    private Date updatedAt;

}
