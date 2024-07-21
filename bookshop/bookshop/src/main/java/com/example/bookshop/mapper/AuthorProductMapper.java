package com.example.bookshop.mapper;

import com.example.bookshop.dto.AuthorProductDTO;
import com.example.bookshop.entity.AuthorProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorProductMapper {
    AuthorProductMapper INSTANCE = Mappers.getMapper(AuthorProductMapper.class);
    AuthorProductDTO toDTO(AuthorProduct authorProduct);
    AuthorProduct toEntity(AuthorProductDTO authorProductDTO);
}
