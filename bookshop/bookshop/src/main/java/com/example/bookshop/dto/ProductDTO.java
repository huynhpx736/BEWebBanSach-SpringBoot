package com.example.bookshop.dto;

import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Category;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.entity.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private Integer publicationYear;
    private Publisher publisher;
    private Integer salesVolume;
    private Float starRating;
    private String image;
    private Float weight;
    private List<Category> categories;
    private List<Author> authors;
    private List<Tag> tags;
    private Float priority;
    private Integer status;
    private Integer quantity_sold;
    private String content;
    private String topic;
}
