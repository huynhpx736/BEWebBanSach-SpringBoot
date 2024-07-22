package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductCategoryDTO;
import com.example.bookshop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategoryDTO toDTO(ProductCategory productCategory);
    ProductCategory toEntity(ProductCategoryDTO productCategoryDTO);
}

