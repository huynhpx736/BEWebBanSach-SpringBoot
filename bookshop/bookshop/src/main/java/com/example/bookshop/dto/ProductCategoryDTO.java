package com.example.bookshop.dto;

import com.example.bookshop.entity.Category;
import com.example.bookshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {
    private Integer id;
//    private Product product;
//    private Category category;
    private Integer productId;
    private Integer categoryId;


}

