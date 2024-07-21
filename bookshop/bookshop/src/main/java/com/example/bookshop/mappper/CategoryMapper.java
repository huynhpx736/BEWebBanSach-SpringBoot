package com.example.bookshop.mappper;

import com.example.bookshop.dto.CategoryDTO;
import com.example.bookshop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

//    @Mapping(target = "id", ignore = true)
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    void updateCategoryFromDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
