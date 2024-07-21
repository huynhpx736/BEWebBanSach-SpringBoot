package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String title;
    private String description;
    private int price;
    private int publicationYear;
    private int categoryId;
    private int publisherId;
    private int salesVolume;
    private float starRating;
    private String image;
    private float weight;
}
