package com.example.bookshop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductSearchCriteria {
    private String title;
    private String author;
    private String category;
    private String publisher;
    private Integer publicationYear;
    private String tag;
    private Float minRating;
    private Float maxRating;
    private Integer minPrice;
    private Integer maxPrice;

    private float titleWeight;
    private float authorWeight;
    private float categoryWeight;
    private float publisherWeight;
    private float yearWeight;
    private float tagWeight;
    private float ratingWeight;
    private float priceWeight;
}
