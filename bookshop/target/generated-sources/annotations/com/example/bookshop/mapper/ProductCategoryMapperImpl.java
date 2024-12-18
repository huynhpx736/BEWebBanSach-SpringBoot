package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductCategoryDTO;
import com.example.bookshop.entity.Category;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.ProductCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-15T15:13:40+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductCategoryMapperImpl implements ProductCategoryMapper {

    @Override
    public ProductCategoryDTO toDTO(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setProductId( productCategoryProductId( productCategory ) );
        productCategoryDTO.setCategoryId( productCategoryCategoryId( productCategory ) );
        productCategoryDTO.setId( productCategory.getId() );

        return productCategoryDTO;
    }

    @Override
    public ProductCategory toEntity(ProductCategoryDTO productCategoryDTO) {
        if ( productCategoryDTO == null ) {
            return null;
        }

        ProductCategory productCategory = new ProductCategory();

        productCategory.setId( productCategoryDTO.getId() );

        return productCategory;
    }

    private Integer productCategoryProductId(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }
        Product product = productCategory.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer productCategoryCategoryId(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }
        Category category = productCategory.getCategory();
        if ( category == null ) {
            return null;
        }
        Integer id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
