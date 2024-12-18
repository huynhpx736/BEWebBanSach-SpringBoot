//package com.example.bookshop.dto;
//
//import lombok.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
////@Data
//public class ProductSearchCriteria {
//    private String title;
//    private String author;
//    private String category;
//    private String publisher;
//    private Integer publicationYear;
//    private String tag;
//    private Float minRating;
//    private Float maxRating;
//    private Integer minPrice;
//    private Integer maxPrice;
//
//    private Float titleWeight;
//    private Float authorWeight;
//    private Float categoryWeight;
//    private Float publisherWeight;
//    private Float yearWeight;
//    private Float tagWeight;
//    private Float ratingWeight;
//    private Float priceWeight;
//}

package com.example.bookshop.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCriteria {
    private String title;
    private String author;
    private String category;
    private String topic;
    private String publisher;
    private Integer publicationYear;
    private String tag;
    private Float minRating;
    private Float maxRating;
    private Integer minPrice;
    private Integer maxPrice;

    private Float titleWeight;
    private Float authorWeight;
    private Float categoryWeight;
    private Float topicWeight;
    private Float publisherWeight;
    private Float yearWeight;
    private Float tagWeight;
    private Float ratingWeight;
    private Float priceWeight;

    private Integer minQuantitySold;
    private Float quantitySoldWeight;
    private String content;
    private Float contentWeight;
}

