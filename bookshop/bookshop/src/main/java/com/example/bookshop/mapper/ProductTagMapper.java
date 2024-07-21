package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.entity.ProductTag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductTagMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductTagDTO toDTO(ProductTag productTag);
    ProductTag toEntity(ProductTagDTO productTagDTO);
}
